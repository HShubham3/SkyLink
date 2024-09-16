package com.scm.skylink.controllers;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scm.skylink.dto.ContactDto;
import com.scm.skylink.dto.ContactSearchDto;
import com.scm.skylink.entities.ContactEntity;
import com.scm.skylink.entities.Helper;
import com.scm.skylink.entities.UserEntity;
import com.scm.skylink.helper.AppConstants;
import com.scm.skylink.helper.Message;
import com.scm.skylink.helper.MessageType;
import com.scm.skylink.repositories.UserRepo;
import com.scm.skylink.services.ContactService;
import com.scm.skylink.services.ImageService;
import com.scm.skylink.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequestMapping("/user/contacts")
@RequiredArgsConstructor
public class ContactsController {

    private final ContactService contactService;

    private final UserService userService;

    private final UserRepo userRepo;

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
    public String saveContact(@Valid @ModelAttribute ContactDto contactDto, BindingResult rBindingResult,
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

    @GetMapping
    public String getContacts(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = AppConstants.PAGE_SIZE + "") int size,
            @RequestParam(value = "sortBy", defaultValue = "name") String sortBy,
            @RequestParam(value = "direction", defaultValue = "asc") String direction,
            Authentication authentication, Model model) {

        String email = Helper.getEmailOfLoggedInUser(authentication);

        Page<ContactEntity> contactPage = contactService.getContactsByUser(userRepo.findByEmail(email).get(), page,
                size,
                sortBy, direction);

        model.addAttribute("contactPage", contactPage);
        log.info("Number of pages :{} \nPage Number :{}", contactPage.getTotalPages(), contactPage.getNumber());
        model.addAttribute("pageSize", AppConstants.PAGE_SIZE);
        model.addAttribute("pageNumber", contactPage.getNumber());
        model.addAttribute("contactSearchDto", new ContactSearchDto());
        return "user/contacts";
    }

    @GetMapping("/search")
    public String searchHandleString(@ModelAttribute ContactSearchDto contactSearchDto,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = AppConstants.PAGE_SIZE + "") int size,
            @RequestParam(value = "sortBy", defaultValue = "name") String sortBy,
            @RequestParam(value = "direction", defaultValue = "asc") String direction,
            Authentication authentication, Model model) {

        log.info("field:{} , Keyword:{}", contactSearchDto.getField(), contactSearchDto.getValue());
        String email = Helper.getEmailOfLoggedInUser(authentication);
        Page<ContactEntity> contacts = null;

        UserEntity user = userRepo.findByEmail(email).get();

        if (contactSearchDto.getField().equalsIgnoreCase("name")) {
            contacts = contactService.searchByName(contactSearchDto.getValue(), page, size, sortBy,
                    direction, user);
        } else if (contactSearchDto.getField().equalsIgnoreCase("email")) {
            contacts = contactService.searchByEmail(contactSearchDto.getValue(), page, size, sortBy, direction, user);
        } else {
            contacts = contactService.searchByPhoneNo(contactSearchDto.getValue(), page, size, sortBy, direction, user);
        }

        log.info("Search Result: {}", contacts);
        model.addAttribute("contactPage", contacts);
        model.addAttribute("contactSearchDto", contactSearchDto);
        log.info("Number of pages :{} \nPage Number :{}", contacts.getTotalPages(), contacts.getNumber());
        model.addAttribute("pageSize", AppConstants.PAGE_SIZE);
        model.addAttribute("pageNumber", contacts.getNumber());
        return "user/search";
    }

    @GetMapping("/view/{contactId}")
    public String getUpdateContactData(@PathVariable Long contactId, Model model) {
        var contactDto = contactService.getContactById(contactId);
        log.info("Contact to be updated : {}", contactDto.getContactId());
        model.addAttribute("contactDto", contactDto);
        return "user/update_contact";
    }

    @PostMapping("/update/{contactId}")
    public String updateContact(@ModelAttribute ContactDto contactDto, @PathVariable Long contactId, Model model) {
        contactService.updateContact(contactId, contactDto);
        System.out.println("ContactDto=" + contactDto);
        return "redirect:/user/contacts";
    }

    @GetMapping("/delete/{contactId}")
    public String deleteContact(@PathVariable Long contactId, Model model) {
        var contactDto = contactService.getContactById(contactId);
        log.info("Contact id={}", contactDto.getContactId());
        model.addAttribute("contactDto", contactDto);
        return "redirect:/user/update_contact";
    }

}
