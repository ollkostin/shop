package ru.practice.kostin.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.practice.kostin.shop.service.CreateProductService;
import ru.practice.kostin.shop.service.dto.product.NewProductDTO;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/products/create")
public class CreateProductController {
    private CreateProductService createProductService;

    @GetMapping
    public String createProduct() {
        return "create-product";
    }

    @PostMapping("/")
    public String createProduct(@ModelAttribute("product") NewProductDTO productDTO, RedirectAttributes redirectAttributes) throws IOException {
        HashMap<String, List<String>> errors = createProductService.createProduct(productDTO);
        if (!errors.isEmpty()) {
            redirectAttributes.addFlashAttribute("product", productDTO);
            redirectAttributes.addFlashAttribute("errors", errors);
        }
        return "redirect:/products/create";

    }

    @Autowired
    public void setCreateProductService(CreateProductService createProductService) {
        this.createProductService = createProductService;
    }
}
