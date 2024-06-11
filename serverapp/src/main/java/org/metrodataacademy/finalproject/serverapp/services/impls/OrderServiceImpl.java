package org.metrodataacademy.finalproject.serverapp.services.impls;

import lombok.extern.slf4j.Slf4j;
import org.metrodataacademy.finalproject.serverapp.models.dtos.requests.OrderRequest;
import org.metrodataacademy.finalproject.serverapp.models.dtos.requests.ReceiptRequest;
import org.metrodataacademy.finalproject.serverapp.models.dtos.responses.OrderDetailsResponse;
import org.metrodataacademy.finalproject.serverapp.models.dtos.responses.OrderResponse;
import org.metrodataacademy.finalproject.serverapp.models.entities.Course;
import org.metrodataacademy.finalproject.serverapp.models.entities.Order;
import org.metrodataacademy.finalproject.serverapp.models.entities.Payment;
import org.metrodataacademy.finalproject.serverapp.models.entities.User;
import org.metrodataacademy.finalproject.serverapp.repositories.CourseRepository;
import org.metrodataacademy.finalproject.serverapp.repositories.OrderRepository;
import org.metrodataacademy.finalproject.serverapp.repositories.PaymentRepository;
import org.metrodataacademy.finalproject.serverapp.repositories.UserRepository;
import org.metrodataacademy.finalproject.serverapp.services.EmailService;
import org.metrodataacademy.finalproject.serverapp.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.mail.MessagingException;

@Service
@Transactional
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private EmailService emailService;

    @Override
    public OrderDetailsResponse getOrderDetailsCourse(String title) {
        try {
            log.info("Try to get order details for course {}", title);
            Course course = courseRepository.findCourseByTitle(title)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found!"));

            final Double tax = 0.11;
            Integer price = course.getPrice();
            Double calculateTax = tax * price;
            Double totalPrice = price + calculateTax;

            return OrderDetailsResponse.builder()
                    .title(course.getTitle())
                    .category(course.getCategories().getName())
                    .mentor(course.getMentor())
                    .price(price)
                    .tax(calculateTax)
                    .totalPrice(totalPrice)
                    .build();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public OrderResponse orderCourse(OrderRequest orderRequest) throws MessagingException{
        try {
            log.info("Trying to purchase a {} course.", orderRequest.getTitle());
            String username = SecurityContextHolder.getContext().getAuthentication().getName();

            if (orderRepository.existsUserOrder(username, orderRequest.getTitle())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You have purchased this course!");
            }

            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));

            Course course = courseRepository.findCourseByTitle(orderRequest.getTitle())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found!"));

            Payment payment = paymentRepository.findById(orderRequest.getPaymentId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Payment not found!"));

            Order order = Order.builder()
                    .time(new Date())
                    .isPaid(true)
                    .payments(payment)
                    .users(user)
                    .courses(course)
                    .build();
            orderRepository.save(order);

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");

            final Double tax = 0.11;
            Integer price = course.getPrice();
            Double calculateTax = tax * price;
            Double totalPrice = price + calculateTax;

            ReceiptRequest receiptRequest = ReceiptRequest.builder()
                    .recipient(user.getEmail())
                    .subject("DevPro Learning Receipt")
                    .orderId(order.getId())
                    .name(user.getName())
                    .title(course.getTitle())
                    .duration(course.getTotalDuration())
                    .price(totalPrice)
                    .orderTime(simpleDateFormat.format(order.getTime()))
                    .paymentMethod(order.getPayments().getName())
                    .paid(order.getIsPaid())
                    .build();
            emailService.sendReceipt(receiptRequest);

            log.info("Course {} purchase successful!", course.getTitle());
            return mapToOrderResponse(order);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderResponse> getPaymentHistory() {
        log.info("Get payment history all user for admin!");
        return orderRepository.findAll().stream()
                .map(this::mapToOrderResponse)
                .collect(Collectors.toList());
    }

    private OrderResponse mapToOrderResponse(Order order) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        return OrderResponse.builder()
                .id(order.getId())
                .course(order.getCourses().getTitle())
                .name(order.getUsers().getName())
                .time(simpleDateFormat.format(order.getTime()))
                .payment(order.getPayments().getName())
                .isPaid(order.getIsPaid())
                .build();
    }
}