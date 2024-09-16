package com.scm.skylink.entities;

import java.util.*;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Table(name = "user_details")
public class UserEntity {

    @Id
    private String userId;

    @Column(name = "user_name")
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "password")
    private String pwd;

    @Column(length = 10000)
    private String about;

    private String profilePic;

    private String phoneNo;

    // information

    private boolean enabled = false;

    private boolean emailVerified = false;

    private boolean phoneNoVerified = false;

    private String roles;

    // SELF , GOOGLE , GITHUB , FACEBOOK ...
    @Enumerated(EnumType.STRING)
    private Providers provider = Providers.SELF;

    private String providerUserId;

    // additional information

    // mapping Contacts

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ContactEntity> contacts;

}
