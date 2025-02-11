package org.metrodataacademy.finalproject.serverapp.repositories;

import org.metrodataacademy.finalproject.serverapp.models.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    Boolean existsByName(String name);
}