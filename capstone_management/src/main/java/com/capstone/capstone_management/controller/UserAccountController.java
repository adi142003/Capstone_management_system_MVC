package com.capstone.capstone_management.controller;

import com.capstone.capstone_management.models.*;
import com.capstone.capstone_management.repository.ProjectRepository;
import com.capstone.capstone_management.service.StudentService;
import com.capstone.capstone_management.service.TeacherService;
import org.springframework.ui.Model;
import com.capstone.capstone_management.dto.UserAccountDto;
import com.capstone.capstone_management.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.List;
import java.util.Objects;

@Controller
public class UserAccountController {
    private final ProjectRepository projectRepository;
    private UserAccountService userAccountService;
    @Autowired
    public UserAccountController(ProjectRepository projectRepository, UserAccountService userAccountService, StudentService studentService, TeacherService teacherService) {
        this.projectRepository = projectRepository;
        this.userAccountService = userAccountService;
        this.studentService = studentService;
        this.teacherService = teacherService;
    }


    private StudentService studentService;
    private TeacherService teacherService;


    @GetMapping("/useraccount")
    public String listUserAccount(Model model) {
        List<UserAccountDto> userAccounts = userAccountService.findAllUserAccounts();
        model.addAttribute("userAccounts", userAccounts);
        return "useraccount-list";
    }

    @GetMapping("/useraccount/login")
    public String login(Model model) {
        UserAccount userAccount = new UserAccount();
        String srn="";
        String trn="";
        model.addAttribute("userAccount", userAccount);
        model.addAttribute("srn",srn);
        model.addAttribute("trn",trn);
        return "useraccount-login";
    }

    @PostMapping("/useraccount/login")
    public String loginSubmit(@ModelAttribute("userAccount") UserAccount userAccount, @ModelAttribute String srn, @ModelAttribute String trn) {
        if(Objects.equals(userAccount.getRole(),"Teaching Faculty")) {
            
        } else if (Objects.equals(userAccount.getRole(),"Student")) {

        }else {

        }
        return "useraccount-list";
    }


    @GetMapping("/useraccount/register")
    public String registerUserAccount(Model model) {
        UserAccount userAccount = new UserAccount();
        model.addAttribute("userAccount", userAccount);
        model.addAttribute("student", new Student());
        model.addAttribute("teacher", new Teacher());
        model.addAttribute("project", new Project());
        return "useraccount-form";
    }

    @PostMapping("/useraccount/register")
    public String registerUserAccount(@ModelAttribute String sr,@ModelAttribute("userAccount") UserAccount userAccount, @ModelAttribute("student") Student student, @ModelAttribute("teacher") Teacher teacher, @ModelAttribute Project project) {
        UserAccount createduser = userAccountService.saveUserAccount(userAccount);
        if (Objects.equals(createduser.getRole(), "Student")){
            student.setUserAccount(createduser);
            System.out.println("Entering if1");
            if(projectRepository.existsByTeamID(project.getTeamID())){
                student.setProject(project);
                studentService.saveStudent(student);
                return "redirect:/useraccount";
            }
            System.out.println("Entering if2");
            studentService.saveStudent(student);
            String srns = student.getSRN();
            sr = srns;
            System.out.println(sr);

            System.out.println("Entering if3");
            if(!projectRepository.existsByTeamID(project.getTeamID())){

                return "redirect:/student/teamreg/"+sr;
            }
        } else if (Objects.equals(createduser.getRole(), "Teaching Faculty")) {
            teacher.setUserAccount(createduser);
            teacherService.saveTeacher(teacher);
        }
        return "redirect:/useraccount";
    }

}
