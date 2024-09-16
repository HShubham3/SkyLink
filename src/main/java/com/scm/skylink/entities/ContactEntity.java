package com.scm.skylink.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
// @ToString
@Table(name = "contacts")
public class ContactEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contactId;

    private String name;

    private String email;

    private String phoneNo;

    private String contactImageUrl;

    private String address;

    private String description;

    private boolean favorite;

    private String webLink;

    private String linkedinLink;

    // mappings contacts , User

    @OneToMany(mappedBy = "contacts", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<SocialLink> links;

    @ManyToOne
    @JoinColumn(name = "userId")
    private UserEntity user;

}
