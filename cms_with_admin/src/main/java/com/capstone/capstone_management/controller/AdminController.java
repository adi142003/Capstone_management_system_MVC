package com.capstone.capstone_management.controller;

import com.capstone.capstone_management.models.Review;
import com.capstone.capstone_management.service.AdminService;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    private AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/useraccount/admin/reviews")
    public String listReviews(Model model) {
        List<Review> reviews = adminService.getAllReviews();
        model.addAttribute("reviews", reviews);
        return "admin-reviews";
    }
    @GetMapping("/useraccount/admin/download-marks")
    public void downloadMarksCSV(HttpServletResponse response) throws IOException {
        // Prepare the CSV file with marks data
        String csvContent = adminService.getMarksCSV();

        // Set the response headers for CSV download
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"marks.csv\"");

        // Write the CSV content to the response output stream
        response.getWriter().write(csvContent);
    }
}

