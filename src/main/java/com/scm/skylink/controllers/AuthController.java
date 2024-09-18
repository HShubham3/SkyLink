package com.scm.skylink.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.scm.skylink.entities.UserEntity;
import com.scm.skylink.repositories.UserRepo;

import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepo userRepo;

    @GetMapping("/verify-email")
    public String getMethodName(@RequestParam String token) {
        log.info("Mail Verified with token: {}", token);

        UserEntity user = userRepo.findByEmailToken(token).orElse(null);

        if (user != null && user.getEmailToken().equals(token)) {
            user.setEnabled(true);
            user.setEmailVerified(true);
            userRepo.save(user);
            return "success_page";
        }

        return "error_page";
    }

}
