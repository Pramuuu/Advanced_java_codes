package com.ManagementSystem.OnlineCourseManagementSystem.service.impl;

import com.ManagementSystem.OnlineCourseManagementSystem.dto.user.RegisterRequestDTO;
import com.ManagementSystem.OnlineCourseManagementSystem.dto.user.UserResponseDTO;
import com.ManagementSystem.OnlineCourseManagementSystem.entity.User;
import com.ManagementSystem.OnlineCourseManagementSystem.exception.ResourceNotFoundException;
import com.ManagementSystem.OnlineCourseManagementSystem.repository.UserRepository;
import com.ManagementSystem.OnlineCourseManagementSystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    // Register User
    @Override
    public UserResponseDTO registerUser(RegisterRequestDTO request) {

        User user = new User();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword()); // In real project → encrypt password
        user.setRole(request.getRole());

        User savedUser = userRepository.save(user);

        return mapToDTO(savedUser);
    }

    // Get User By ID
    @Override
    public Optional<UserResponseDTO> getUserById(Long id) {

        return userRepository.findById(id)
                .map(this::mapToDTO);
    }

    // Login User (Simple Demo Logic)
    @Override
    public UserResponseDTO loginUser(String email, String password) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Invalid email or password"));

        if (!user.getPassword().equals(password)) {
            throw new ResourceNotFoundException("Invalid email or password");
        }

        return mapToDTO(user);
    }

    private UserResponseDTO mapToDTO(User user) {

        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setFullName(user.getFullName());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        dto.setProfilePicture(user.getProfilePicture());
        dto.setCreatedAt(user.getCreatedAt());

        return dto;
    }
}