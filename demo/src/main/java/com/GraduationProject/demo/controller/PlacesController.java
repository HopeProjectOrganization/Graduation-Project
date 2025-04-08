package com.GraduationProject.demo.controller;

import com.GraduationProject.demo.model.Places;
import com.GraduationProject.demo.service.PlacesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Places")
@Slf4j
public class PlacesController {
    @Autowired
    private PlacesService service;
    @GetMapping("/name/{name}")
    public ResponseEntity<?> getPlaceByName(@PathVariable String name) {
        Places place = service.getByName(name);
        return new ResponseEntity<>(place, HttpStatus.OK);
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<?> getPlaceById(@PathVariable Integer id) {
        Places place = service.getById(id);
        return new ResponseEntity<>(place, HttpStatus.OK);
    }

}
