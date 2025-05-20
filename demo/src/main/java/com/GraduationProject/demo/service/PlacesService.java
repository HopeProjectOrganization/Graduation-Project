package com.GraduationProject.demo.service;

import com.GraduationProject.demo.model.HereditaryPeople;
import com.GraduationProject.demo.model.Places;
import com.GraduationProject.demo.repo.PlacesRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Places createNewsPlace(Places post) {return placesrepo.save(post);}

    public Places updatePlace(Integer id, Places updatedPlace) {
        Places existingPlace = placesrepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Place not found with id " + id));

        existingPlace.setHospitalName(updatedPlace.getHospitalName());
        existingPlace.setHospitalNumber(updatedPlace.getHospitalNumber());
        existingPlace.setHospitalAddress(updatedPlace.getHospitalAddress());
        existingPlace.setHospitalLocation(updatedPlace.getHospitalLocation());
        existingPlace.setHospitalWebsite(updatedPlace.getHospitalWebsite());
        existingPlace.setLogo(updatedPlace.getLogo());

        return placesrepo.save(existingPlace);
    }

    public void deletePlace(Integer id) {
        if (!placesrepo.existsById(id)) {
            throw new RuntimeException("Place not found with id " + id);
        }
        placesrepo.deleteById(id);
    }

    public List<Places> getAll() {
        return placesrepo.findAll();
    }
}
