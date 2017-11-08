package ru.practice.kostin.shop.controller;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.practice.kostin.shop.service.ProductService;
import ru.practice.kostin.shop.service.dto.product.ProductShortDTO;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private ProductService productService;

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
    public ResponseEntity getProduct(@PathVariable("id") Integer productId) throws NotFoundException {
        return ok(productService.getProduct(productId));
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
}
