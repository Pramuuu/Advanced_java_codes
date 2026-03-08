package com.ManagementSystem.OnlineCourseManagementSystem.service;

import com.ManagementSystem.OnlineCourseManagementSystem.dto.user.RegisterRequestDTO;
import com.ManagementSystem.OnlineCourseManagementSystem.dto.user.UserResponseDTO;

import java.util.Optional;

public interface UserService {

    UserResponseDTO registerUser(RegisterRequestDTO request);

    Optional<UserResponseDTO> getUserById(Long id);

    UserResponseDTO loginUser(String email, String password);
}