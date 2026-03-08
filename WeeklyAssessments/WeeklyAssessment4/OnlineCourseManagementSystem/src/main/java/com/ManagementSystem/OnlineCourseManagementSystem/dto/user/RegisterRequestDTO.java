package com.ManagementSystem.OnlineCourseManagementSystem.dto.user;

import com.ManagementSystem.OnlineCourseManagementSystem.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequestDTO {

    @NotBlank(message = "Full name is required")
    private String fullName;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;

    @Size(min = 6, message = "Password must contain minimum 6 characters")
    private String password;

    private Role role;
}