package org.metrodataacademy.finalproject.serverapp.repositories;

import org.metrodataacademy.finalproject.serverapp.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);

    Boolean existsByEmail(String email);

    Boolean existsByPhone(String phone);

    Boolean existsByUsername(String username);

    @Query("SELECT COUNT(u) > 0 FROM User u WHERE u.email= :email AND u.id != :id")
    Boolean existsByEmailAndNotId(String email, Integer id);

    @Query("SELECT COUNT(u) > 0 FROM User u WHERE u.phone = :phone AND u.id != :id")
    Boolean existsByPhoneAndNotId(String phone, Integer id);
}