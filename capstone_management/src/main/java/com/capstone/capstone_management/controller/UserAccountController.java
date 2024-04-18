package com.capstone.capstone_management.controller;

import com.capstone.capstone_management.models.UserAccount;
import org.springframework.ui.Model;
import com.capstone.capstone_management.dto.UserAccountDto;
import com.capstone.capstone_management.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserAccountController {
    private UserAccountService userAccountService;

    @Autowired
    public UserAccountController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @GetMapping("/useraccount")
    public String listUserAccount(Model model) {
        List<UserAccountDto> userAccounts = userAccountService.findAllUserAccounts();
        model.addAttribute("userAccounts", userAccounts);
        return "useraccount-list";
    }

    @GetMapping("/useraccount/register")
    public String registerUserAccount(Model model) {
        UserAccount userAccount = new UserAccount();
        model.addAttribute("userAccount", userAccount);
        return "useraccount-form";
    }

    @PostMapping("/useraccount/register")
    public String registerUserAccount(@ModelAttribute("userAccount") UserAccount userAccount) {
        UserAccount createduser = userAccountService.saveUserAccount(userAccount);
        return "redirect:/useraccount";
    }
}
