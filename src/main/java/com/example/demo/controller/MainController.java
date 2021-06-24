package com.example.demo.controller;

import com.example.demo.DemoApplication;
import com.example.demo.model.Contact;
import com.example.demo.model.User;
import com.example.demo.service.ContactService;
import com.example.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {
    private final UserService userService;
    private final ContactService contactService;
    private static final Logger log =  LoggerFactory.getLogger(DemoApplication.class);

    public MainController(UserService userService, ContactService contactService) {
        this.userService = userService;
        this.contactService = contactService;
    }

    @GetMapping("/login")
    public String loginView() {
        return "login";
    }

    @GetMapping("/signup")
    public String signupView(@ModelAttribute(value = "user") User user) {
        user.setContact(new Contact());
        return "signup";
    }

    @PostMapping("/signup")
    public String signupUser(@ModelAttribute(value = "user") User user, Model model) {
        String signupError = null;

        if (!userService.isUsernameAvailable(user.getUsername())) {
            signupError = "The username already exists.";
        }

        if (signupError == null) {
            try{
                log.info("User ():" + user.getContact().getFirstName() + " 0 " + user.getContact().getLastName() +" " + user.getContact().getAddress());
                userService.save(user);
            }catch (Exception ex){
                signupError = "There was an error signing you up. Please try again.";
            }
        }

        if (signupError == null) {
            model.addAttribute("signupSuccess", true);
        } else {
            model.addAttribute("signupError", signupError);
        }

        return "signup";
    }

    @GetMapping("/home")
    public String getHomePage(Authentication authentication,  Model model) {
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        model.addAttribute("username", username);
        model.addAttribute("user", user);
        log.info("User () ->:" + user.getContact().getFirstName() + " 0 " + user.getContact().getLastName() +" " + user.getContact().getAddress());
        model.addAttribute("contacts", contactService.findAll());
        model.addAttribute("users",userService.findAll());
        return "home";
    }

    @PostMapping("/home")
    public String postChatMessage(Authentication authentication, @ModelAttribute User user, Model model) {
        String signupError = null;

        if (!userService.isUsernameAvailable(user.getUsername())) {
            signupError = "The username already exists.";
        }

        if (signupError == null) {
            try{
                log.info("User ():" + user.getContact().getFirstName() + " 0 " + user.getContact().getLastName() +" " + user.getContact().getAddress());
                userService.save(user);
            }catch (Exception ex){
                signupError = "There was an error editing you up. Please try again.";
            }
        }

        if (signupError == null) {
            model.addAttribute("signupSuccess", true);
        } else {
            model.addAttribute("signupError", signupError);
        }

        ;
        return "home";
    }
}
