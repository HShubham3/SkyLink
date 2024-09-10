package com.scm.skylink.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scm.skylink.dto.ContactDto;
import com.scm.skylink.entities.Helper;
import com.scm.skylink.entities.UserEntity;
import com.scm.skylink.services.ContactService;
import com.scm.skylink.services.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/user/contacts")
@RequiredArgsConstructor
public class ContactsController {

    private final ContactService contactService;

    private final UserService userService;

    private final ModelMapper modelMapper;

    // contact page : handller

    @GetMapping("/add")
    public String getAddContactView(Model model) {
        ContactDto contactDto = new ContactDto();
        // contactDto.setFavorite(true);
        model.addAttribute("contactForm", contactDto);
        return "user/addContacts";
    }

    @PostMapping("/add")
    public String saveContact(@ModelAttribute ContactDto contactDto, Authentication authentication, Model model) {
        // proccess contact

        // get user mail id
        String email = Helper.getEmailOfLoggedInUser(authentication);
        UserEntity user = modelMapper.map(userService.getUserByEmail(email), UserEntity.class);

        // set user
        contactDto.setUser(user);
        // save contact
        ContactDto contactDto2 = contactService.saveContact(contactDto);

        return "redirect:/user/contacts/add";
    }

}
