package ru.practice.kostin.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.practice.kostin.shop.security.CustomUserDetails;
import ru.practice.kostin.shop.service.CartService;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/cart/")
public class UserCartController {
    private CartService cartService;

    @GetMapping
    public ResponseEntity getCart(){
        CustomUserDetails user = (CustomUserDetails)(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return ok().body(cartService.getCart(user.getId()));
    }

    @Autowired
    public void setCartService(CartService cartService) {
        this.cartService = cartService;
    }
}
