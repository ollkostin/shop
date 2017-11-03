package ru.practice.kostin.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.practice.kostin.shop.exception.PasswordMismatchException;
import ru.practice.kostin.shop.exception.UserAlreadyExistsException;
import ru.practice.kostin.shop.service.UserService;
import ru.practice.kostin.shop.service.dto.product.NewUserDTO;

@Controller
@RequestMapping("/register")
public class RegisterController {
    private UserService userService;

    @GetMapping
    public String registerForm() {
        return "register";
    }

    /**
     * Creates user
     * @param userDTO user info
     * @param redirectAttributes redirect attributes
     * @return login page
     * @throws PasswordMismatchException password mismatch
     */
    @PostMapping
    public String signUp(@ModelAttribute("user") NewUserDTO userDTO, RedirectAttributes redirectAttributes) throws PasswordMismatchException {
        try {
            userService.createUser(userDTO);
        } catch (UserAlreadyExistsException | PasswordMismatchException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/register";
        } finally {
            redirectAttributes.addFlashAttribute("user", userDTO);
        }
        return "redirect:/login";
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
