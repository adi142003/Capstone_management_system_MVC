package com.capstone.capstone_management.controller;

import com.capstone.capstone_management.models.Project;
import com.capstone.capstone_management.models.Review;
import com.capstone.capstone_management.models.Student;
import com.capstone.capstone_management.service.StudentService;
import com.capstone.capstone_management.service.TeacherService;

import java.time.Year;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller
public class TeacherController {
    private TeacherService teacherService;
    private StudentService studentService;

    public TeacherController(TeacherService teacherService, StudentService studentService) {
        this.teacherService = teacherService;
        this.studentService = studentService;
    }

    @GetMapping("/teacher/projects")
    public String getProjectsForTeacher(@RequestParam("id") int id,Model model){
        // Assuming you have a way to get the logged-in teacher's ID
        // int teacherId = getLoggedInTeacherId();
        int teacherId = id;

        List<Project> projects = teacherService.getProjectsByTeacherId(teacherId);
        model.addAttribute("projects", projects);

        return "teacher-projects";
    }

    

    @GetMapping("/projects/year")
    public String getProjectsByYear(@RequestParam(value = "year", required = false) Integer year, Model model) {
        List<Project> projects;
        if (year != null) {
            projects = teacherService.getProjectsByYear(year);
        } else {
            projects = teacherService.getAllProjects();
        }
        model.addAttribute("projects", projects);

        return "projects-by-year";
    }
    


    private int getLoggedInTeacherId() {
        // Implement your logic to retrieve the logged-in teacher's ID
        // For example, from the session or a security context
        return 1; // Replace with the actual teacher ID
    }
}
