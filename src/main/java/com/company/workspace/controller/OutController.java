package com.company.workspace.controller;

import com.company.workspace.service.user.UserService;
import com.company.workspace.service.userDetails.UserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class OutController {

    private final UserService userService;
    private final UserDetailsService userDetailsService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @GetMapping("/registration/user")
    public String registrationAsUser(Model model) {
        model.addAttribute("userForUser", userService.createUser());
        model.addAttribute("userDetails", userDetailsService.createUserDetails());
        return "registration_user";
    }

    @GetMapping("/registration/company")
    public String registrationAsCompany(Model model) {
        model.addAttribute("userForCompany", userService.createUser());
        return "registration_company";
    }
}