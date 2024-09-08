package com.scm.skylink.controllers.advice;

import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.scm.skylink.entities.Helper;
import com.scm.skylink.services.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class RootController {

    private final UserService userService;

    @ModelAttribute
    public void loggedInUserInfo(Authentication authentication, Model model) {
        if (authentication == null)
            return;
        String email = Helper.getEmailOfLoggedInUser(authentication);
        log.info("Email of loggedIn user is: {}", email);
        model.addAttribute("userDetails", userService.getUserByEmail(email));
    }

}
