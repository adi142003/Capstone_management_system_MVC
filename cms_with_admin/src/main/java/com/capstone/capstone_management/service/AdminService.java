package com.capstone.capstone_management.service;

import org.springframework.stereotype.Service;
import com.capstone.capstone_management.models.Review;

import java.util.List;

public interface AdminService {
    List<Review> getAllReviews();
    String getMarksCSV();
}