package com.capstone.capstone_management.controller;

import com.capstone.capstone_management.dto.UserAccountDto;
import com.capstone.capstone_management.models.Project;
import com.capstone.capstone_management.models.Student;
import com.capstone.capstone_management.models.Teacher;
import com.capstone.capstone_management.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class StudentController {
    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/student/teamreg/{srn}")
    public String TeamReg(Model model, @PathVariable String srn) {
        model.addAttribute("project",new Project());
        model.addAttribute("srn",srn);
        model.addAttribute("teacher",new Teacher());
        return "team-registration";
    }

    @GetMapping("/project/dashboard/{srn}")
    public String Dashboard(Model model, @PathVariable String srn) {
        return "layout";
    }

    @PostMapping("/student/teamreg/{srn}")
    public String TeamRegSubmit(@PathVariable String srn, @ModelAttribute("project") Project project, @ModelAttribute Teacher teacher) {
        Student student = new Student();
        student.setSRN(srn);
        project.setTeacher(teacher);
        project.addStudent(student);
        Project proj = studentService.saveProject(project);
        return "redirect:/project/dashboard/{srn}";
    }

}
