package com.selfSpring.UserManagerment.controller;

import com.selfSpring.UserManagerment.domain.User;
import com.selfSpring.UserManagerment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/members/new")
    public String newUser(UserForm form) {
        User user = new User();
        user.setName(form.getName());

        userService.join(user);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String users(Model model) {
        List<User> users = userService.findUsers();
        model.addAttribute("users", users);

        return "members/memberList";
    }
}
