package com.carsy.controller;

import com.carsy.controller.dto.LoginDto;
import com.carsy.controller.dto.RegistrationDto;
import com.carsy.controller.dto.UserInfoDto;
import com.carsy.exception.UserException;
import com.carsy.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@CrossOrigin
@RequestMapping("/")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/")
    public ModelAndView mainMenu() {
        return new ModelAndView("index");
    }

    @GetMapping("/login")
    public ModelAndView loginForm() {
        return new ModelAndView("loginPage");
    }

    @PostMapping("/login/perform")
    public ModelAndView performLoginUser(@Valid @ModelAttribute("loginDto") LoginDto retrievedData) {
        UserInfoDto userInformation;
        try {
             userInformation = userService.loginUser(retrievedData);
        } catch (UserException e) {
            return new ModelAndView("loginPage", "exception", e.getMessage());
        }
        return new ModelAndView("index", "currentUser", userInformation);
    }

    @GetMapping("/register")
    public ModelAndView registerForm() {
        return new ModelAndView("registrationPage");
    }

    @PostMapping("/register/perform")
    public ModelAndView performRegisterUser(@Valid @ModelAttribute("registrationDto") RegistrationDto retrievedData) {
        UserInfoDto userInformation;
        try {
            userInformation = userService.registerUser(retrievedData);
        } catch (UserException e) {
            return new ModelAndView("registrationPage", "exception", e.getMessage());
        }
        return new ModelAndView("index", "currentUser", userInformation);
    }

    @GetMapping("/logout/perform")
    public ModelAndView performLogout() {
        return new ModelAndView("index");
    }
}
