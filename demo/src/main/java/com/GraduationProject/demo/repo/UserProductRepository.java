package com.GraduationProject.demo.repo;

import com.GraduationProject.demo.model.UserProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserProductRepository extends JpaRepository<UserProduct, Integer> {
}
