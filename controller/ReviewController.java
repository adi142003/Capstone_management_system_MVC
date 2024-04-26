package com.capstone.capstone_management.controller;

import com.capstone.capstone_management.models.Student;
import com.capstone.capstone_management.models.Teacher;
import com.capstone.capstone_management.models.UserAccount;
import com.capstone.capstone_management.repository.UserAccountRepository;
import com.capstone.capstone_management.service.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.capstone.capstone_management.models.Review;

import org.springframework.ui.Model;

import java.time.LocalDate;

@Controller
public class ReviewController {
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private TeacherService teacherService;

    public ReviewController(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    private UserAccountRepository userAccountRepository;

    @GetMapping("/reviews/form/{tsn}")
    public String getReviewForm(@PathVariable String tsn, Model model) {

        model.addAttribute("review", new Review());
        model.addAttribute("tsn",tsn);
        model.addAttribute("srn",new SRN());
        return "review-form";
    }

    @PostMapping("/reviews/submit/{tsn}")
    public String submitReview(@PathVariable String tsn, @ModelAttribute("review") Review review, @ModelAttribute("srn") SRN srn) {
        UserAccount u = userAccountRepository.findByUsn(srn.getSrn());
        Student s = studentService.findStudent(u);
        review.setStudent(s);
        Teacher t = teacherService.getTeacher(tsn);
        review.setTeacher(t);
        reviewService.saveReview(review);
        // return "redirect:/reviews/success";
        return "redirect:/reviews/form/"+tsn;
    }

    @Data
    public class SRN {
        private String srn;
    }
}