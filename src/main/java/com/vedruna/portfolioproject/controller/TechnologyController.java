package com.vedruna.portfolioproject.controller;

import com.vedruna.portfolioproject.persistance.models.Technology;
import com.vedruna.portfolioproject.services.TechnologyServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/technologies")
@CrossOrigin
public class TechnologyController {

    @Autowired
    private TechnologyServiceI technologyService;

    @PostMapping("/insert")
    public ResponseEntity<Technology> createTechnology(@RequestBody Technology technology) {
        return technologyService.saveTechnology(technology);
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTechnology(@PathVariable int id) {
        return technologyService.deleteTechnology(id);
    }
}