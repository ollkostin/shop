package ru.practice.kostin.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.practice.kostin.shop.exception.PasswordMismatchException;
import ru.practice.kostin.shop.exception.UserAlreadyExistsException;
import ru.practice.kostin.shop.security.AuthenticationService;
import ru.practice.kostin.shop.service.UserService;
import ru.practice.kostin.shop.service.dto.product.NewUserDTO;

@Controller
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegisterController {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    @GetMapping
    public String registerForm() {
        return "register";
    }

    /**
     * Creates user
     *
     * @param userDTO            user info
     * @param redirectAttributes redirect attributes
     * @return login page
     */
    @PostMapping
    public String signUp(@ModelAttribute("user") NewUserDTO userDTO, RedirectAttributes redirectAttributes) {
        try {
            userService.createUser(userDTO);
        } catch (UserAlreadyExistsException | PasswordMismatchException e) {
            redirectAttributes.addFlashAttribute("user", userDTO);
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/register";
        }
        authenticationService.authenticateUser(userDTO);
        return "redirect:/";
    }
}
