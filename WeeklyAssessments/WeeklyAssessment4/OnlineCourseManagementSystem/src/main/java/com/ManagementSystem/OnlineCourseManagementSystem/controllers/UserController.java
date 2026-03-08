package com.ManagementSystem.OnlineCourseManagementSystem.controllers;

import com.ManagementSystem.OnlineCourseManagementSystem.dto.user.UserResponseDTO;
import com.ManagementSystem.OnlineCourseManagementSystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public UserResponseDTO getUserById(@PathVariable Long id) {

        return userService.getUserById(id)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));
    }
}