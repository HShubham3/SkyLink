package com.scm.skylink.dto;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.scm.skylink.entities.SocialLink;
import com.scm.skylink.entities.UserEntity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ContactDto {

    private Long contactId;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Email is required.")
    @Email(message = "Invalid email.")
    private String email;

    @NotBlank(message = "Phone number is required.")
    @Pattern(regexp = "^[0-9]{10}$", message = "Invalid Phone number.")
    private String phoneNo;

    @NotBlank(message = "Address is required.")
    private String address;

    private String description;

    private MultipartFile contactImage;

    private String contactImageUrl;

    private boolean favorite;

    private String webLink;

    private String linkedinLink;

    private List<SocialLink> links;

    @JsonIgnore
    private UserEntity user;

}
