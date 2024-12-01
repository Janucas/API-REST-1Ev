package com.vedruna.portfolioproject.controller;

import com.vedruna.portfolioproject.persistance.models.Technology;
import com.vedruna.portfolioproject.services.TechnologyServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/technologies")
@CrossOrigin //Para poder interactuar entre fron(cliente) y back(servidor)
public class TechnologyController {

    @Autowired
    private TechnologyServiceI technologyService;

    //Metodo para insertar una nueva tecnologia
    @PostMapping("/insert")
    public ResponseEntity<Technology> createTechnology(@RequestBody Technology technology) {
        return technologyService.saveTechnology(technology);
    }

    //Metodo para borrar una tecnologia introduciendo su id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTechnology(@PathVariable int id) {
        return technologyService.deleteTechnology(id);
    }
}