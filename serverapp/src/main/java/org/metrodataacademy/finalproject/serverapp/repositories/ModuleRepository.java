package org.metrodataacademy.finalproject.serverapp.repositories;

import org.metrodataacademy.finalproject.serverapp.models.entities.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Integer> {

}