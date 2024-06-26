package org.metrodataacademy.finalproject.clientapp.controllers;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import org.metrodataacademy.finalproject.clientapp.models.dtos.responses.CourseDetailsResponse;
import org.metrodataacademy.finalproject.clientapp.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CourseControllerThymeleaf {

    @Autowired
    private CourseService courseService;

    @GetMapping(
    path = "/course/details/{title}"
)
public String courseDetailsView(@PathVariable String title, Model model) {
    try {
        String decodedTitle = URLDecoder.decode(title, StandardCharsets.UTF_8.toString());
        CourseDetailsResponse courseDetailsResponse = courseService.courseDetails(decodedTitle);
        model.addAttribute("course", courseDetailsResponse);
        return "pages/user/course-details"; // course-details or details
    } catch (UnsupportedEncodingException e) {
        // Log error
        e.printStackTrace();
        return "error-page"; // Atau halaman error yang sesuai
    }
}

    @GetMapping(
        path = "/my-course",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String myCourseView(){
        return "pages/user/my-course";
    }
    
}