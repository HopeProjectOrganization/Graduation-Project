package com.GraduationProject.demo.repo;

import com.GraduationProject.demo.model.Ingredient;
import com.GraduationProject.demo.model.Places;
import com.GraduationProject.demo.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlacesRepo extends JpaRepository<Places, Integer> {
    Optional<Places> findByHospitalName(String hospitalName);}
