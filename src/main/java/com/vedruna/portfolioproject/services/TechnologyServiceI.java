package com.vedruna.portfolioproject.services;

import org.springframework.http.ResponseEntity;

import com.vedruna.portfolioproject.persistance.models.Technology;

public interface TechnologyServiceI {
    ResponseEntity<Technology> saveTechnology(Technology technology);
    ResponseEntity<Void> deleteTechnology(int id);
}
