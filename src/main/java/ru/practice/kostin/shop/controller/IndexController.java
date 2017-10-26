package ru.practice.kostin.shop.controller;

import org.springframework.stereotype.Controller;
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

    @GetMapping("/upload")
    public String uploadForm() {
        return "upload-form";
    }

    @GetMapping("/products/{productId}")
    public String product(@PathVariable("productId") Integer productId) {
        return "product";
    }

    @GetMapping("/cart")
    public String cart(){
        return "cart";
    }
}
