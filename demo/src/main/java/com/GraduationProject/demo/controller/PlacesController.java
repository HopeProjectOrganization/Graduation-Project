package com.GraduationProject.demo.controller;

import com.GraduationProject.demo.model.HereditaryPeople;
import com.GraduationProject.demo.model.Places;
import com.GraduationProject.demo.service.PlacesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/all")
    public ResponseEntity<List<Places>> getAllPlaces() {
        List<Places> places = service.getAll();
        return new ResponseEntity<>(places, HttpStatus.OK);
    }

    @PostMapping("/addPlace")
public Places createNewsPlace(@RequestBody Places post) {return service.createNewsPlace(post); }

    @PutMapping("/{id}")
    public ResponseEntity<Places> updatePlace(@PathVariable Integer id, @RequestBody Places updatedPlace) {
        Places place = service.updatePlace(id, updatedPlace);
        return ResponseEntity.ok(place);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlace(@PathVariable Integer id) {
       service.deletePlace(id);
        return ResponseEntity.noContent().build();
    }
}
