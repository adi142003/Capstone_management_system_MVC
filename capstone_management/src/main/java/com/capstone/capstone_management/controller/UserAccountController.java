package com.capstone.capstone_management.controller;

import com.capstone.capstone_management.models.*;
import com.capstone.capstone_management.repository.ProjectRepository;
import com.capstone.capstone_management.service.StudentService;
import com.capstone.capstone_management.service.TeacherService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import com.capstone.capstone_management.dto.UserAccountDto;
import com.capstone.capstone_management.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Controller
public class UserAccountController {
    private final ProjectRepository projectRepository;
    private final HandlerExceptionResolver handlerExceptionResolver;
    private UserAccountService userAccountService;
    @Autowired
    public UserAccountController(ProjectRepository projectRepository, UserAccountService userAccountService, StudentService studentService, TeacherService teacherService, @Qualifier("handlerExceptionResolver") HandlerExceptionResolver handlerExceptionResolver) {
        this.projectRepository = projectRepository;
        this.userAccountService = userAccountService;
        this.studentService = studentService;
        this.teacherService = teacherService;
        this.handlerExceptionResolver = handlerExceptionResolver;
    }


    private StudentService studentService;
    private TeacherService teacherService;

    @GetMapping("")
    public String welcomePage(){
        return "welcome-page";
    }

    @GetMapping("/useraccount")
    public String listUserAccount(Model model) {
        List<UserAccountDto> userAccounts = userAccountService.findAllUserAccounts();
        model.addAttribute("userAccounts", userAccounts);
        return "useraccount-list";
    }

    @GetMapping("/useraccount/login")
    public String login(Model model) {
        UserAccount userAccount = new UserAccount();
        model.addAttribute("userAccount", userAccount);
        return "useraccount-login";
    }

    @PostMapping("/useraccount/login")
    public String loginSubmit(@ModelAttribute("userAccount") UserAccount userAccount) {
        String pass;
            try{
                pass = userAccountService.GetPassword(userAccount.getUsn());
            }catch (NoSuchElementException e){
                System.out.println("No Record Found");
                return "redirect:/useraccount/register";
            }catch (NullPointerException e){
                System.out.println("No Record Found");
                return "redirect:/useraccount/register";
            }
            if(Objects.equals(pass,userAccount.getPassword())) {
                System.out.println("Success");
            }
            else{
                System.out.println("Fail");
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
            if(projectRepository.existsByTeamid(project.getTeamid())){
                student.setProject(project);
                Project proj = studentService.findProject(project.getTeamid());
                student.setProject(proj);
                studentService.saveStudent(student);
                return "redirect:/useraccount";
            }
            System.out.println("Entering if2");
            studentService.saveStudent(student);
            String srns = userAccount.getUsn();
            sr = srns;
            System.out.println(sr);

            System.out.println("Entering if3");
            if(!projectRepository.existsByTeamid(project.getTeamid())){

                return "redirect:/student/teamreg/"+sr;
            }
        } else if (Objects.equals(createduser.getRole(), "Teaching Faculty")) {
            teacher.setUseraccount(createduser);
            teacherService.saveTeacher(teacher);
        }
        return "redirect:/useraccount";
    }

}
