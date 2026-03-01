package com.LMS.Springmvc.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class User {
    private int id;

    @NotBlank(message="Name cannot be blank")
    @Size(min=3,message="Name should have atleast 3 characters")
    private String name;


    @NotBlank(message="Email cannot be blank")
    @Email(message="Enter the valid email")
    private String email;

    @NotBlank(message="Password cannot be blank")
    @Size(min=6,message="Password should be atleast 6 characters")
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
