package com.vedruna.portfolioproject.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.vedruna.portfolioproject.persistance.models.Developer;

public interface DeveloperServiceI {
   
    ResponseEntity<Developer> saveDeveloper(Developer developer); //Crear un nuevo programador
    ResponseEntity<Void> deleteDeveloper(int id); //Borrar un programador introduciendo su id
    
}
