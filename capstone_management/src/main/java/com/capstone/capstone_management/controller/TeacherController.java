package com.capstone.capstone_management.controller;

import com.capstone.capstone_management.service.TeacherService;
import org.springframework.stereotype.Controller;

@Controller
public class TeacherController {
    private TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }
}
