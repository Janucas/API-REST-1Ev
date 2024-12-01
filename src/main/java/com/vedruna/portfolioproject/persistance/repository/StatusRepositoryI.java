package com.vedruna.portfolioproject.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vedruna.portfolioproject.persistance.models.Status;

public interface StatusRepositoryI extends JpaRepository<Status, Integer> {
    
}
