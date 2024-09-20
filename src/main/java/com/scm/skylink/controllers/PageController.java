package com.scm.skylink.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.scm.skylink.dto.UserDto;
import com.scm.skylink.helper.Message;
import com.scm.skylink.helper.MessageType;
import com.scm.skylink.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PageController {

    private final UserService userService;

    @GetMapping("/")
    public String index() {
        return "redirect:/home";
    }

    // Home Page

    @GetMapping("/home")
    public String homePage(Model model) {
        model.addAttribute("Name", "Shubham H");
        return "home";
    }

    // About Page

    @GetMapping("/about")
    public String aboutPage(Model model) {
        model.addAttribute("Name", "Shubham H");
        return "about";
    }

    // Sevice Page

    @GetMapping("/services")
    public String sevicesPage(Model model) {
        model.addAttribute("Name", "Shubham H");
        return "services";
    }

    // Contacts Page

    @GetMapping("/contacts")
    public String contactsPage() {
        return "contacts";
    }

    // Login Page

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    // @PostMapping("/login")
    // public String login() {
    // return "login";
    // }

    // Register Page

    @GetMapping("/register")
    public String registerPage(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("UserDto", userDto);
        return "register";
    }

    // Proccessing register

    @PostMapping("/do-register")
    public String createNewUser(@ModelAttribute @Valid UserDto userDto, BindingResult rBindingResult,
            HttpSession session) {

        // validate form data
        if (rBindingResult.hasErrors()) {
            System.out.println(rBindingResult.toString());
            return "register";
        }
        userDto.setProfilePic(
                "https://www.google.com/url?sa=i&url=https%3A%2F%2Farchive.org%2Fdetails%2Finstagram-plain-round&psig=AOvVaw0PSv_BPj9YEUuBctBd3jac&ust=1726729220338000&source=images&cd=vfe&opi=89978449&ved=0CBQQjRxqFwoTCIi89-L1y4gDFQAAAAAdAAAAABAE");
        userService.createNewUser(userDto);
        Message message = Message.builder().content("Registration successfull")
                .type(MessageType.green)
                .build();
        session.setAttribute("message", message);
        return "emailsent";
    }

}
