package com.company.workspace.controller;

import com.company.workspace.entity.User;
import com.company.workspace.entity.UserDetails;
import com.company.workspace.handler.UserRegistrationException;
import com.company.workspace.service.user.UserService;
import com.company.workspace.service.userDetails.UserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @PostMapping("/registration/user")
    public String registrationAsUserPost(@ModelAttribute("userForUser") User user, @ModelAttribute("userDetails") UserDetails userDetails) {
        System.out.println("POST --------- METHOD");
        userService.checkUser(user);
        userDetailsService.checkUserDetails(userDetails);
        userDetailsService.saveUserDetails(userDetails);
        userService.setUserDetails(user, userDetails);
        userService.saveUser(user);
        return "redirect:/login";
    }

    @GetMapping("/registration/error")
    public String registrationError() {
        return "registration_error";
    }

    @GetMapping("/registration/company")
    public String registrationAsCompany(Model model) {
        model.addAttribute("userForCompany", userService.createUser());
        return "registration_company";
    }

    @ExceptionHandler(UserRegistrationException.class)
    public String handleUserRegistrationException(UserRegistrationException ex,RedirectAttributes redirectAttributes) {
        System.out.println("-------------| handleUserRegistrationException |-------------");
        redirectAttributes.addFlashAttribute("error", ex.getMessage());
        return "redirect:/registration/error";
    }
}