package com.capstone.capstone_management.controller;

import com.capstone.capstone_management.dto.UserAccountDto;
import com.capstone.capstone_management.models.Project;
import com.capstone.capstone_management.models.Student;
import com.capstone.capstone_management.models.Teacher;
import com.capstone.capstone_management.models.UserAccount;
import com.capstone.capstone_management.service.StudentService;
import com.capstone.capstone_management.service.TeacherService;
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
    private TeacherService teacherService;

    @Autowired
    public StudentController(StudentService studentService, TeacherService teacherService) {
        this.studentService = studentService;
        this.teacherService = teacherService;
    }

    @GetMapping("/student/teamreg/{srn}")
    public String TeamReg(Model model, @PathVariable String srn) {
        model.addAttribute("project",new Project());
        model.addAttribute("srn",srn);
        model.addAttribute("teacher",new UserAccount());
        return "team-registration";
    }

    @GetMapping("/project/dashboard/{srn}")
    public String Dashboard(Model model, @PathVariable String srn) {
        return "layout";
    }

    @PostMapping("/student/teamreg/{srn}")
    public String TeamRegSubmit(@PathVariable String srn, @ModelAttribute("project") Project project, @ModelAttribute UserAccount teacher) {
        UserAccount student = new UserAccount();
        student.setUsn(srn);
        Student stu = new Student();
        stu.setUserAccount(student);
        Teacher te = teacherService.getTeacher(teacher.getUsn());
        project.setTeacher(te);
        project.addStudent(stu);
        Project proj = studentService.saveProject(project);
        return "redirect:/project/dashboard/{srn}";
    }

}
