package ru.practice.kostin.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.practice.kostin.shop.service.ProductService;
import ru.practice.kostin.shop.service.dto.product.ProductShortDTO;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    /**
     * Returns list of products for current page
     *
     * @param pageable pagination parameters
     * @return list of products
     */
    @GetMapping("/")
    public ResponseEntity getProducts(@PageableDefault Pageable pageable) {
        Page<ProductShortDTO> products = productService.getProducts(pageable);
        return ok(products);
    }

    /**
     * Returns product by id
     * or message that there is no product
     * with specified id
     *
     * @param productId id of the product
     * @return product
     */
    @GetMapping("/{id}")
    public ResponseEntity getProduct(@PathVariable("id") Integer productId) {
        return ok(productService.getProduct(productId));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize(value = "hasRole('ROLE_VENDOR') and hasRole('ROLE_ADMIN')")
    public ResponseEntity deleteProduct(@PathVariable("id") Integer productId) {
        productService.deleteProduct(productId);
        return ok().build();
    }

    @PutMapping("/{id}")
    @PreAuthorize(value = "hasRole('ROLE_VENDOR') and hasRole('ROLE_ADMIN')")
    public ResponseEntity restoreProduct(@PathVariable("id") Integer productId) {
        productService.restoreProduct(productId);
        return ok().build();
    }

}
