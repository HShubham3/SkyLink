package com.scm.skylink.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class UserController {

    // user dashboard

    @GetMapping("/dashboard")
    public String getUserDashboard() {
        return "user/dashboard";
    }

    // user profile page

    @GetMapping("/profile")
    public String getUserProfile() {
        return "user/profile";
    }

    // user add contact page

    // user delete contact page

    // user edit contact page

    // user view contact page

    // user search page

}
