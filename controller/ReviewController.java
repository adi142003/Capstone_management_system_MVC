package com.capstone.capstone_management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.capstone.capstone_management.models.Project;
import com.capstone.capstone_management.models.Review;
import com.capstone.capstone_management.service.ProjectService;
import com.capstone.capstone_management.service.ReviewService;
import com.capstone.capstone_management.service.TeacherService;

import org.springframework.ui.Model;

@Controller
public class ReviewController {
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private TeacherService teacherService;

    @GetMapping("/reviews/form")
    public String getReviewForm(Model model) {
        model.addAttribute("review", new Review());
        return "review-form";
    }

    @PostMapping("/reviews/submit")
    public String submitReview(Review review) {
        reviewService.saveReview(review);
        // return "redirect:/reviews/success";
        return "redirect:/reviews/form";
    }
}
