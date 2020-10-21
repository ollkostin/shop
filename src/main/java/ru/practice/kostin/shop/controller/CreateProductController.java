package ru.practice.kostin.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.practice.kostin.shop.service.CreateProductService;
import ru.practice.kostin.shop.service.dto.product.NewProductDTO;

import java.net.URI;
import java.util.HashMap;
import java.util.List;

import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.created;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class CreateProductController {
    private final CreateProductService createProductService;

    /**
     * Creates product.
     * If info was fulfilled incorrect returns list of errors.
     *
     * @param productDTO product info
     * @return page of the product
     */
    @PostMapping("/")
    public ResponseEntity<?> createProduct(@ModelAttribute("product") NewProductDTO productDTO) {
        HashMap<String, List<String>> errors = createProductService.createProduct(productDTO);
        if (!errors.isEmpty()) {
            return badRequest().body(errors);
        }
        return created(URI.create("")).body(productDTO.getId());
    }
}
