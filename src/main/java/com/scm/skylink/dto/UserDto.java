package com.scm.skylink.dto;

import java.util.List;

import com.scm.skylink.entities.Contact;
import com.scm.skylink.entities.ContactEntity;
import com.scm.skylink.entities.Providers;

import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
public class UserDto {

    private String userId;

    @NotBlank(message = "*Username is required.")
    @Size(min = 3, message = "*Min 3 charectors required.")
    private String name;

    @Email(message = "Invalid email")
    private String email;

    @NotBlank(message = "*Password is required")
    @Size(min = 6, message = "*Min 6 charector required.")
    private String pwd;

    @NotBlank(message = "*About is required")
    private String about;

    private String profilePic;

    @Size(min = 8, max = 12, message = "*Invalid Phone Number")
    private String phoneNo;

    private boolean enabled = false;

    private boolean emailVerified = false;

    private boolean phoneNoVerified = false;

    @Enumerated
    private Providers provider = Providers.SELF;

    private String providerUserId;

    private List<ContactEntity> contacts;

}
