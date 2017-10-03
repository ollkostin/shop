package ru.practice.kostin.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/products")
    public String products() {
        return "products";
    }

    @GetMapping("/products/{id}")
    public String product(@PathVariable Integer id, Model model) {
        model.addAttribute("id", id);
        return "product";
    }
}
