package com.company.workspace.controller;

import com.company.workspace.entity.User;
import com.company.workspace.entity.UserDetails;
import com.company.workspace.service.user.UserService;
import com.company.workspace.service.userDetails.UserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


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
    public String registrationAsUserPost(@Valid @ModelAttribute("userForUser") User user,
                                         @Valid @ModelAttribute("userDetails") UserDetails userDetails,
                                         BindingResult bindingResult,
                                         RedirectAttributes redirectAttributes,
                                         Model model) {
        System.out.println("------------------| POST METHOD |-------------------");
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userForUser", bindingResult);
            redirectAttributes.addFlashAttribute("userForUser", user);
            redirectAttributes.addFlashAttribute("userDetails", userDetails);
            return "registration_user";
        }
        userService.setUserDetails(user,userDetails);
        userDetailsService.setUser(userDetails,user);
        userService.saveUser(user);
        userDetailsService.saveUserDetails(userDetails);
        return "redirect:/login";
    }

    @GetMapping("/registration/company")
    public String registrationAsCompany(Model model) {
        model.addAttribute("userForCompany", userService.createUser());
        return "registration_company";
    }
}