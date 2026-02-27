package org.example.controller;

import org.example.model.User;
import org.example.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



@Controller
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    // Home Page
    @GetMapping({"/", "/home"})
    public String home() {
        return "home";
    }
    // List Users
    @GetMapping("/users")
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "userList";
    }
    // View Single User
    @GetMapping("/user/{id}")
    public String viewUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "userDetail";
    }
    // Show Add Form
    @GetMapping("/addUser")
    public String showAddForm() {
        return "addUser";
    }
    @PostMapping("/addUser")
    public String addUser(@RequestParam("name") String name, @RequestParam("email") String email) {
        Long id = (long)(Math.random()*1000);
        User user = new User(id, name, email);
        userService.addUser(user);
        return "redirect:/users";
    }
}