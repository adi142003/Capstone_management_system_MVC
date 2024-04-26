package com.capstone.capstone_management.service.impl;

import com.capstone.capstone_management.models.Review;
import com.capstone.capstone_management.repository.ReviewRepository;
import com.capstone.capstone_management.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    ReviewRepository reviewRepository;

    public void saveReview(Review review) {
        reviewRepository.save(review);
    }
}
