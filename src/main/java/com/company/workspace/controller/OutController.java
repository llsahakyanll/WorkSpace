package com.company.workspace.controller;

import org.apache.log4j.Logger;
import com.company.workspace.dto.UserDTO;
import com.company.workspace.dto.VerificationDTO;
import com.company.workspace.entity.User;
import com.company.workspace.entity.UserDetails;
import com.company.workspace.handler.UserLoginException;
import com.company.workspace.handler.UserRegistrationException;
import com.company.workspace.service.email.EmailService;
import com.company.workspace.service.user.UserService;
import com.company.workspace.service.userDetails.UserDetailsService;
import com.company.workspace.service.verification.VerificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class OutController {

    private final UserService userService;
    private final UserDetailsService userDetailsService;
    private final EmailService emailService;
    private final VerificationService verificationService;
    private static final Logger logger = Logger.getLogger(OutController.class);

    // -----------------------------| Login |----------------------------- //

    @GetMapping("/login")
    public String login(Model model) {
        logger.info("/login + in Login Method");
        model.addAttribute("user", userService.createUserDTO());
        return "login";
    }

    @PostMapping("/authenticate")
    public String loginPost(@ModelAttribute("user") UserDTO userDTO) {
        logger.info("/authenticate");
        logger.info(userDTO.toString());
        userService.checkUser(userDTO);
        logger.info("End /authenticate");
        return "redirect:/home";
    }

    @GetMapping("/login/error")
    public String loginError(Model model) {
        logger.info("/login/error");
        model.addAttribute("user", userService.createUserDTO());
        return "login";
    }

    // -----------------------------| Registration |----------------------------- //

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
    public String registrationAsUserPost(@ModelAttribute("userForUser") User user, @ModelAttribute("userDetails") UserDetails userDetails, RedirectAttributes redirectAttributes) {
        userService.checkUserEmail(user);
        userDetailsService.checkUserDetails(userDetails);
        userDetailsService.saveUserDetails(userDetails);
        userService.setUserDetails(user, userDetails);
        userService.saveUser(user);
        redirectAttributes.addFlashAttribute("email" , user);
        return "redirect:/registration/mail";
    }

    @GetMapping("/registration/mail")
    public String registrationMail(@ModelAttribute("email") User user, Model model) {
        VerificationDTO verificationDTO = new VerificationDTO();
        verificationDTO.setCode(emailService.sendVerificationCode(user.getEmail()));
        verificationDTO.setUserId(user.getId());
        model.addAttribute("verificationDTO", verificationDTO);
        return "registration_mail";
    }

    @PostMapping("/registration/mail")
    public String registrationMailPost(@ModelAttribute("verificationDTO") VerificationDTO verificationDTO) {
        verificationService.checkVerificationCode(verificationDTO.getCode(), verificationDTO.getNumber().toString());
        return "redirect:/login";
    }

    @GetMapping("/registration/company")
    public String registrationAsCompany(Model model) {
        model.addAttribute("userForCompany", userService.createUser());
        return "registration_company";
    }

    @GetMapping("/registration/error")
    public String registrationError() {
        return "registration_error";
    }

    // -----------------------------| ExceptionHandler |----------------------------- //

    @ExceptionHandler(UserRegistrationException.class)
    public String handleUserRegistrationException(UserRegistrationException ex,RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("error", ex.getMessage());
        return "redirect:/registration/error";
    }

    @ExceptionHandler(UserLoginException.class)
    public String handleUserLoginException(UserLoginException ex,RedirectAttributes redirectAttributes) {
        logger.info("@ExceptionHandler(UserLoginException.class)");
        redirectAttributes.addFlashAttribute("error", ex.getMessage());
        return "redirect:/login/error";
    }
}