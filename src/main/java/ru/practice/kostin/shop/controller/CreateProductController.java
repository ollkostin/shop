package ru.practice.kostin.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.practice.kostin.shop.exception.FileTooLargeException;
import ru.practice.kostin.shop.exception.UnsupportedExtensionException;
import ru.practice.kostin.shop.service.CreateProductService;
import ru.practice.kostin.shop.service.dto.product.NewProductDTO;

import java.io.IOException;

@Controller
@RequestMapping("/products/create")
public class CreateProductController {
    private CreateProductService createProductService;

    @GetMapping
    public String createProduct() {
        return "create-product";
    }

    @PostMapping("/")
    public String createProduct(@ModelAttribute("product") NewProductDTO productDTO, Model model) {
        try {
            createProductService.createProduct(productDTO);
        } catch (IllegalArgumentException | UnsupportedExtensionException | FileTooLargeException | IOException e) {
            model.addAttribute("error", e.getMessage());
        }
        model.addAttribute("product", productDTO);
        return "create-product";
    }

    @Autowired
    public void setCreateProductService(CreateProductService createProductService) {
        this.createProductService = createProductService;
    }
}
