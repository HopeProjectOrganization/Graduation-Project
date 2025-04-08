package com.GraduationProject.demo.service;

import com.GraduationProject.demo.model.Places;
import com.GraduationProject.demo.repo.PlacesRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlacesService {

    private final PlacesRepo placesrepo;

    public Places getByName(String Name) {
        return placesrepo.findByHospitalName( Name).get();
    }

    public Places getById(Integer id) {
        return placesrepo.findById(id).get();
    }
}
