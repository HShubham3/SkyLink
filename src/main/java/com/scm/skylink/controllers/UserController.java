package com.scm.skylink.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scm.skylink.services.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // user dashboard
    @GetMapping("/dashboard")
    public String getUserDashboard() {
        return "user/dashboard";
    }

    // user profile page
    @GetMapping("/profile")
    public String getUserProfile(Authentication authentication, Model model) {

        return "user/profile";
    }

    // user add contact page

    // user delete contact page

    // user edit contact page

    // user view contact page

    // user search page

}
