package com.capstone.capstone_management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.capstone_management.models.Review;
import com.capstone.capstone_management.repository.ReviewRepository;

@Service
public interface ReviewService {

    void saveReview(Review review);
}