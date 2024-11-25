package com.vedruna.portfolioproject.persistance.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.vedruna.portfolioproject.persistance.models.Project;

public interface ProjectRepositoryI extends JpaRepository<Project, Integer> {
    Page<Project> findAll(Pageable pageable);
    public Optional<Project> findByProjectName(String projectName);//Optional significa que si es nulo o esta vacio me va a devolver una excepcion interna (no necesita crear una excepcion)
}




