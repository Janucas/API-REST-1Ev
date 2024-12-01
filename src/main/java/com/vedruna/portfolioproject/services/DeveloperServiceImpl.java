package com.vedruna.portfolioproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.vedruna.portfolioproject.persistance.models.Developer;
import com.vedruna.portfolioproject.persistance.repository.DeveloperRepositoryI;

@Service
public class DeveloperServiceImpl implements DeveloperServiceI {
    
    @Autowired
    private DeveloperRepositoryI developerRepository;

    @Override
    public ResponseEntity<Developer> saveDeveloper(Developer developer) {
        Developer savedDeveloper = developerRepository.save(developer);
        return ResponseEntity.ok(savedDeveloper);
    }

  

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

