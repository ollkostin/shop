package ru.practice.kostin.shop.controller;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practice.kostin.shop.service.ProductService;
import ru.practice.kostin.shop.service.dto.product.ProductFullDTO;
import ru.practice.kostin.shop.service.dto.product.ProductShortDTO;

import java.util.List;


@RestController
@RequestMapping("/product")
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
        List<ProductShortDTO> products = productService.getProducts(pageable);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    /**
     * Returns product by id
     * or message that there is no product
     * with specified id
     *
     * @param productId  id of the product
     * @return product
     */
    @GetMapping("/{id}")
    public ResponseEntity getProduct(@PathVariable("id") Integer productId) {
        try {
            ProductFullDTO product = productService.getProduct(productId);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
}
