package com.scm.skylink.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.scm.skylink.entities.SocialLink;
import com.scm.skylink.entities.UserEntity;

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

    private String name;

    private String email;

    private String phoneNo;

    private String address;

    private String description;

    private MultipartFile profilePic;

    private boolean favorite;

    private String webLink;

    private String linkedinLink;

    private List<SocialLink> links;

    private UserEntity user;

}
