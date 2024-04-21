package com.capstone.capstone_management.service;

import org.springframework.stereotype.Service;

import com.capstone.capstone_management.models.Panel;
import com.capstone.capstone_management.models.Review;
import com.capstone.capstone_management.models.Teacher;

import java.util.List;

public interface AdminService {
    List<Review> getAllReviews();
    String getMarksCSV();
List<Teacher> getAllTeachers();
void savePanel(Panel panel);
}
