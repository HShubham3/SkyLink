package com.scm.skylink.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scm.skylink.dto.ContactDto;
import com.scm.skylink.services.ContactService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class APIController {

    private final ContactService contactService;

    @GetMapping("/contacts/{id}")
    public ContactDto getContactData(@PathVariable Long id) {
        return contactService.getContactById(id);
    }

}
