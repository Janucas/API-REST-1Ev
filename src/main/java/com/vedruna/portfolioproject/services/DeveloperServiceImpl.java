package com.vedruna.portfolioproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.vedruna.portfolioproject.persistance.models.Developer;
import com.vedruna.portfolioproject.persistance.repository.DeveloperRepositoryI;

@Service
public class DeveloperServiceImpl implements DeveloperServiceI {
    
    //Interactua con la capa anterior
    @Autowired
    private DeveloperRepositoryI developerRepository;


    //Metodo para crear un nuevo desarrollador
    @Override
    public ResponseEntity<Developer> saveDeveloper(Developer developer) {
        Developer savedDeveloper = developerRepository.save(developer);
        return ResponseEntity.ok(savedDeveloper);
    }

    //Metodo para borrar un desarrollador
    @Override
    public ResponseEntity<Void> deleteDeveloper(int id) {
        if (developerRepository.existsById(id)) {
            developerRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

