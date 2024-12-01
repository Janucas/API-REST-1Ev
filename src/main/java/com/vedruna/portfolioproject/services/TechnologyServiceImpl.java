package com.vedruna.portfolioproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.vedruna.portfolioproject.persistance.models.Technology;
import com.vedruna.portfolioproject.persistance.repository.TechnologyRepositoryI;

@Service
public class TechnologyServiceImpl implements TechnologyServiceI{

    //Interaccion con la capa anyerior
     @Autowired
    private TechnologyRepositoryI technologyRepository;

   
    //Metodo para crear una nueva tecnologia
    @Override
    public ResponseEntity<Technology> saveTechnology(Technology technology) {
        Technology savedTechnology = technologyRepository.save(technology);
        return ResponseEntity.ok(savedTechnology);
    }


    //Metodo para borrar una tecnologia por su id
    @Override
    public ResponseEntity<Void> deleteTechnology(int id) {
        if (technologyRepository.existsById(id)) {
            technologyRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
    

