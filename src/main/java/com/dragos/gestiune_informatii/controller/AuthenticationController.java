package com.dragos.gestiune_informatii.controller;

import com.dragos.gestiune_informatii.model.User;
import com.dragos.gestiune_informatii.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthenticationController {

    @Autowired
    private UserService userService;

    // Show registration form
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "register";  // Render the register page
    }

    // Register new user
    @PostMapping("/register")
    public String registerUser(User user) {
        userService.registerNewUser(user);
        return "redirect:/login";  // Redirect to login page after successful registration
    }

    // Show login form (default Spring Security login page)
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";  // Render the login page
    }

    // Logout user
    @GetMapping("/logout")
    public String logout() {
        SecurityContextHolder.clearContext();
        return "redirect:/login";  // Redirect to login after logout
    }
}
