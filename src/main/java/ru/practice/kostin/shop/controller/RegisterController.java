package ru.practice.kostin.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.practice.kostin.shop.exception.UserAlreadyExistsException;
import ru.practice.kostin.shop.service.UserService;

@Controller
public class RegisterController {
    private UserService userService;

    @GetMapping("/register")
    public String registerForm() {
        return "register";
    }


    @PostMapping("/register")
    public String signUp(@RequestParam("email") String email,
                         @RequestParam("password") String password) throws UserAlreadyExistsException {
        userService.createUser(email, password);
        return "redirect:/login";
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
