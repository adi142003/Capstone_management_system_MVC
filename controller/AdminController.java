package com.capstone.capstone_management.controller;

import com.capstone.capstone_management.models.Review;
import com.capstone.capstone_management.models.Teacher;
import com.capstone.capstone_management.models.UserAccount;
import com.capstone.capstone_management.repository.PanelRepository;
import com.capstone.capstone_management.repository.TeacherRepository;
import com.capstone.capstone_management.repository.UserAccountRepository;
import com.capstone.capstone_management.service.AdminService;
import lombok.Data;
import groovyjarjarantlr4.v4.parse.ANTLRParser.parserRule_return;

import com.capstone.capstone_management.models.Panel;

import jakarta.servlet.http.HttpServletResponse;//provide HTTP-specific functionality in sending a response

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired; //automatic dependency injection
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {
    private final TeacherRepository teacherRepository;
    private final PanelRepository panelRepository;
    private AdminService adminService;
    private UserAccountRepository userAccountRepository;

    @Autowired
    public AdminController(AdminService adminService, TeacherRepository teacherRepository, UserAccountRepository userAccountRepository, PanelRepository panelRepository) {
        this.adminService = adminService;
        this.teacherRepository = teacherRepository;
        this.userAccountRepository = userAccountRepository;
        this.panelRepository = panelRepository;
    }

    @GetMapping("/admin/reviews")
    public String listReviews(Model model) {
        List<Review> reviews = adminService.getAllReviews();
        model.addAttribute("reviews", reviews);
        return "admin-reviews";
    }
    @GetMapping("/admin/download-marks")
    public void downloadMarksCSV(HttpServletResponse response) throws IOException {
        // Prepare the CSV file with marks data
        String csvContent = adminService.getMarksCSV();

        // Set the response headers for CSV download
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"marks.csv\"");
        //Content-Disposition is an optional header and allows the sender to indicate a default filename
        // Write the CSV content to the response output stream
        response.getWriter().write(csvContent);
    }
    @GetMapping("/admin/create-panel")
    public String createPanel(Model model) {
        model.addAttribute("panel", new Panel());
        model.addAttribute("teachers", new Teachers());
        return "create-panel";
    }

    @PostMapping("/admin/create-panel")
    public String createPanelSubmit(@ModelAttribute("panel") Panel panel,@ModelAttribute("teachers") Teachers teachers) {
        UserAccount us = userAccountRepository.findByUsn(teachers.getTeacher1());
        Teacher t1 = teacherRepository.findByUseraccount(us);
        us = userAccountRepository.findByUsn(teachers.getTeacher2());
        Teacher t2 = teacherRepository.findByUseraccount(us);
        us = userAccountRepository.findByUsn(teachers.getTeacher3());
        Teacher t3 = teacherRepository.findByUseraccount(us);
        panel.addTeacher(t1);
        panel.addTeacher(t2);
        panel.addTeacher(t3);
        panelRepository.save(panel);
        t1.setPanel(panel);
        t2.setPanel(panel);
        t3.setPanel(panel);
        teacherRepository.save(t1);
        teacherRepository.save(t2);
        teacherRepository.save(t3);
        return "redirect:/admin/reviews";
    }

    @Data
    public class Teachers {
        private String teacher1;
        private String teacher2;
        private String teacher3;
    }
}