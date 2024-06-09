package org.metrodataacademy.finalproject.serverapp.repositories;

import org.metrodataacademy.finalproject.serverapp.models.entities.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Integer> {

    @Query("SELECT COUNT(m) > 0 FROM Module m WHERE m.name = :name AND m.id != :id")
    Boolean existsByNameAndNotId(String name, Integer id);
}