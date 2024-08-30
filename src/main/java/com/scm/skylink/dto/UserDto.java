package com.scm.skylink.dto;

import java.util.List;

import com.scm.skylink.entities.Contact;
import com.scm.skylink.entities.Providers;

import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserDto {

    private String userId;

    private String name;

    private String email;

    private String pwd;

    private String about;

    private String profilePic;

    private String phoneNo;

    private boolean enabled = false;

    private boolean emailVerified = false;

    private boolean phoneNoVerified = false;

    @Enumerated
    private Providers provider = Providers.SELF;

    private String providerUserId;

    private List<Contact> contacts;

}
