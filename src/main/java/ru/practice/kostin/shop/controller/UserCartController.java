package ru.practice.kostin.shop.controller;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.practice.kostin.shop.exception.NotAllowedException;
import ru.practice.kostin.shop.security.CustomUserDetails;
import ru.practice.kostin.shop.service.CartService;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/cart")
public class UserCartController {
    private CartService cartService;

    /**
     * Returns products in user's cart
     *
     * @return list of products
     */
    @GetMapping("/")
    public ResponseEntity getCart() {
        CustomUserDetails user = (CustomUserDetails)
                (SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return ok().body(cartService.getProductsInCart(user.getId()));
    }

    /**
     * Adds product to user's cart
     *
     * @param productId id of product
     * @return http status OK
     * @throws NotFoundException if product was not found
     */
    @PutMapping("/product/{productId}")
    public ResponseEntity addProductToCart(@PathVariable("productId") Integer productId) throws NotFoundException {
        CustomUserDetails user = (CustomUserDetails)
                (SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        cartService.addProductToCart(productId, user.getId());
        return ok().build();
    }

    /**
     * Removes product from user's cart
     *
     * @param productId id of product
     * @return http status ok
     * @throws NotAllowedException if cart does not contain specified product
     */
    @DeleteMapping("/product/{productId}")
    public ResponseEntity removeProductFromCart(@PathVariable("productId") Integer productId) throws NotAllowedException {
        CustomUserDetails user = (CustomUserDetails)
                (SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        cartService.removeProductFromCart(productId, user.getId());
        return ok().build();
    }

    /**
     * Clears user's cart
     *
     * @return http status OK
     */
    @DeleteMapping("/")
    public ResponseEntity clearCart() {
        CustomUserDetails user = (CustomUserDetails)
                (SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        cartService.clearCart(user.getId());
        return ok().build();
    }

    @Autowired
    public void setCartService(CartService cartService) {
        this.cartService = cartService;
    }
}
