package com.ManagementSystem.OnlineCourseManagementSystem.controllers;

import com.ManagementSystem.OnlineCourseManagementSystem.dto.user.LoginRequestDTO;
import com.ManagementSystem.OnlineCourseManagementSystem.dto.user.RegisterRequestDTO;
import com.ManagementSystem.OnlineCourseManagementSystem.dto.user.UserResponseDTO;
import com.ManagementSystem.OnlineCourseManagementSystem.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public UserResponseDTO registerUser(
            @Valid @RequestBody RegisterRequestDTO requestDTO) {

        return userService.registerUser(requestDTO);
    }

    @PostMapping("/login")
    public UserResponseDTO loginUser(
            @RequestBody LoginRequestDTO requestDTO) {

        return userService.loginUser(
                requestDTO.getEmail(),
                requestDTO.getPassword()
        );
    }
}