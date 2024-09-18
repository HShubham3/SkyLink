package com.scm.skylink.entities;

import org.springframework.security.core.Authentication;

public class Helper {

    public static String getEmailOfLoggedInUser(Authentication authentication) {

        return authentication.getName();
    }

    public static String getLinkForEmailVerification(String emailToken) {

        String link = "http://localhost:4545/auth/verify-email?token=" + emailToken;

        return link;

    }

}
