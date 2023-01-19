package com.carsy.service.impl;

import com.carsy.controller.dto.LoginDto;
import com.carsy.controller.dto.RegistrationDto;
import com.carsy.controller.dto.UserInfoDto;
import com.carsy.exception.UnauthorizedException;
import com.carsy.exception.UserAlreadyRegisteredException;
import com.carsy.exception.UserNotFoundException;
import com.carsy.model.user.Role;
import com.carsy.model.user.User;
import com.carsy.repository.user.UserRepository;
import com.carsy.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceimpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserInfoDto loginUser(LoginDto loginDto) {
        User foundUser = this.findUserByEmail(loginDto.getEmail());
        this.checkPassword(loginDto.getPassword(), foundUser.getPassword());
        return UserInfoDto.builder()
                .email(foundUser.getEmail())
                .name(foundUser.getName())
                .build();
    }

    @Override
    public UserInfoDto registerUser(RegistrationDto registrationDto) {
        if (userRepository.findUserByEmail(registrationDto.getEmail()).isPresent()) {
            throw new UserAlreadyRegisteredException(String.format("User with email %s is already registered!", registrationDto.getEmail()));
        }
        String encodedPassword = passwordEncoder.encode(registrationDto.getPassword());

        User newUser = User.builder()
                .email(registrationDto.getEmail())
                .name(registrationDto.getName())
                .password(encodedPassword)
                .roles(this.giveUserRoleOnRegistration())
                .build();
        userRepository.save(newUser);

        return UserInfoDto.builder()
                .email(newUser.getEmail())
                .name(newUser.getName())
                .build();
    }

    private User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(String.format("User with email %s not found!", email)));
    }

    private void checkPassword(String rawPassword, String encoderPassword) {
        if (!passwordEncoder.matches(rawPassword, encoderPassword)) {
            throw new UnauthorizedException("Password incorrect!");
        }
    }

    private Set<Role> giveUserRoleOnRegistration() {
        Set<Role> roles = new HashSet<>();
        Role gainedRole = new Role();

        gainedRole.setName("USER");
        roles.add(gainedRole);

        return roles;
    }
}
