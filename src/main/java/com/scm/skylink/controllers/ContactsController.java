package com.scm.skylink.controllers;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scm.skylink.dto.ContactDto;
import com.scm.skylink.entities.Helper;
import com.scm.skylink.entities.UserEntity;
import com.scm.skylink.helper.Message;
import com.scm.skylink.helper.MessageType;
import com.scm.skylink.services.ContactService;
import com.scm.skylink.services.ImageService;
import com.scm.skylink.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequestMapping("/user/contacts")
@RequiredArgsConstructor
public class ContactsController {

    private final ContactService contactService;

    private final UserService userService;

    private final ImageService imageService;

    private final ModelMapper modelMapper;

    // contact page : handller

    @GetMapping("/add")
    public String getAddContactView(Model model) {
        ContactDto contactDto = new ContactDto();
        // contactDto.setFavorite(true);
        model.addAttribute("contactDto", contactDto);
        return "user/addContacts";
    }

    @PostMapping("/add")
    public String saveContact(@ModelAttribute ContactDto contactDto, BindingResult rBindingResult,
            HttpSession session,
            Authentication authentication,
            Model model) {

        // Image proccessing
        String fileURL = "";
        // Image upload
        if (contactDto.getContactImage() != null && !contactDto.getContactImage().isEmpty()) {
            String filename = UUID.randomUUID().toString();
            fileURL = imageService.uploadImage(contactDto.getContactImage(), filename);
            log.info("COntact image url :{}", fileURL);
        }

        // form validation

        if (rBindingResult.hasErrors()) {
            session.setAttribute("message", Message.builder()
                    .content("Please correct the following errors.")
                    .type(MessageType.red)
                    .build());
            return "user/addContacts";
        }

        // get user mail id
        String email = Helper.getEmailOfLoggedInUser(authentication);
        UserEntity user = modelMapper.map(userService.getUserByEmail(email), UserEntity.class);

        // set user & Image URL
        contactDto.setUser(user);
        contactDto.setContactImageUrl(fileURL);
        // save contact
        ContactDto contactDto2 = contactService.saveContact(contactDto);

        // success message
        session.setAttribute("message", Message.builder()
                .content("You are successfully added a contact.")
                .type(MessageType.green)
                .build());

        return "redirect:/user/contacts/add";
    }

}
