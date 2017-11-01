package ru.practice.kostin.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class IndexController {

    @GetMapping(value = {"/", "/products"})
    public String products() {
        return "products";
    }

    @GetMapping("/upload")
    public String uploadForm() {
        return "upload-form";
    }

    @GetMapping("/products/{productId}")
    public String product(@PathVariable("productId") Integer productId) {
        return "product";
    }

    @GetMapping("/cart")
    public String cart() {
        return "cart";
    }
}
