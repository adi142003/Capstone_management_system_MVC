package com.capstone.capstone_management.controller;

import com.capstone.capstone_management.models.Review;
import com.capstone.capstone_management.models.Teacher;
import com.capstone.capstone_management.service.AdminService;
import com.capstone.capstone_management.models.Panel;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
    @GetMapping("/useraccount/admin/create-panel")
    public String createPanel(Model model) {
        model.addAttribute("panel", new Panel());
        List<Teacher> teachers = adminService.getAllTeachers();
        model.addAttribute("teachers", teachers);
        return "create-panel";
    }

    @PostMapping("/useraccount/admin/create-panel")
    public String createPanelSubmit(@ModelAttribute("panel") Panel panel) {
        adminService.savePanel(panel);
        return "redirect:/useraccount/admin/panels";
    }

}

