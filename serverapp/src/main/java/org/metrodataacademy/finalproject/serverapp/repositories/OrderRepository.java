package org.metrodataacademy.finalproject.serverapp.repositories;

import org.metrodataacademy.finalproject.serverapp.models.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {

    @Query("SELECT COUNT(*) > 0 FROM Order o WHERE o.users.username = :username AND o.courses.title = :title")
    Boolean existsUserOrder(String username, String title);
}