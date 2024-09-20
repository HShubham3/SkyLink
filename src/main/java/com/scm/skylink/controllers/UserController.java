package com.scm.skylink.controllers;

import java.util.UUID;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scm.skylink.dto.UserDto;
import com.scm.skylink.helper.Message;
import com.scm.skylink.helper.MessageType;
import com.scm.skylink.services.ImageService;
import com.scm.skylink.services.UserService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final ImageService imageService;

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

    @GetMapping("/update")
    public String getUserView() {
        return "user/update_user";
    }

    @PostMapping("/update/{userId}")
    public String updateUserDetails(@PathVariable String userId, UserDto userDto, HttpSession session) {
        String link = imageService.uploadImage(userDto.getUserPicture(), UUID.randomUUID().toString());
        userDto.setProfilePic(link);
        userService.updateUserDetails(userId, userDto);
        session.setAttribute("message",
                Message.builder().type(MessageType.green).content("Details successfully updated!").build());
        return "user/profile";
    }

    // user view contact page

    // user search page

}
