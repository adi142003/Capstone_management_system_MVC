package com.capstone.capstone_management.repository;

import com.capstone.capstone_management.models.Review;
import com.capstone.capstone_management.models.Student;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {

      List<Review> findByStudent(Student student);
}
