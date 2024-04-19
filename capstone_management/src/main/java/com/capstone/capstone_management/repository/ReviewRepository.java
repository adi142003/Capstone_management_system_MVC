package com.capstone.capstone_management.repository;

import com.capstone.capstone_management.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
