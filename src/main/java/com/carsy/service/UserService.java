package com.carsy.service;

import com.carsy.controller.dto.LoginDto;
import com.carsy.controller.dto.RegistrationDto;
import com.carsy.controller.dto.UserInfoDto;


/**
 * Service that provide methods for working with the user
 */
public interface UserService {

    /**
     * Method for user authorization
     *
     * @param loginDto retrieved data from user during filling login form
     * @return User name
     */
    UserInfoDto loginUser(LoginDto loginDto);

    /**
     * Method for user registration
     *
     * @param registrationDto retrieved data from user during filling registering form
     */
    UserInfoDto registerUser(RegistrationDto registrationDto);
}
