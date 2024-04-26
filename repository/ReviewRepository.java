package com.capstone.capstone_management.repository;

import com.capstone.capstone_management.models.Review;
import com.capstone.capstone_management.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findByStudent(Student student);
}
