package com.vedruna.portfolioproject.controller;

import com.vedruna.portfolioproject.persistance.models.Developer;
import com.vedruna.portfolioproject.services.DeveloperServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/api/v1/developers")
@CrossOrigin //Para poder interactuar entre fron(cliente) y back(servidor)
public class DeveloperController {

    @Autowired
    private DeveloperServiceI developerService;

    //Metodo para insertar un desarrollador o programador
    @PostMapping("/insert")
    public ResponseEntity<Developer> createDeveloper(@RequestBody Developer developer) {
        return developerService.saveDeveloper(developer);
    }

    //Metodo para borrar un desarrollador o programador introduciendo su id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDeveloper(@PathVariable int id) {
        return developerService.deleteDeveloper(id);
    }
}
