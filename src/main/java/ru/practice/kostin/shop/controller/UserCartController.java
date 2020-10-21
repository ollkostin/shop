package ru.practice.kostin.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.practice.kostin.shop.security.CustomUserDetails;
import ru.practice.kostin.shop.service.CartService;
import ru.practice.kostin.shop.service.dto.product.ProductInCartDTO;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class UserCartController {
    private final CartService cartService;

    /**
     * Returns products in user's cart
     *
     * @return list of products
     */
    @GetMapping("/")
    public ResponseEntity<?> getCart(@PageableDefault Pageable pageable, @AuthenticationPrincipal CustomUserDetails user) {
        return ok().body(cartService.getProductsInCart(user.getId(), pageable));
    }

    /**
     * Adds product to user's cart
     *
     * @param productId id of product
     * @param user      current user
     * @return http status OK
     */
    @PutMapping("/products/{productId}")
    public ResponseEntity<Void> addProductToCart(@PathVariable("productId") Integer productId, @AuthenticationPrincipal CustomUserDetails user) {
        cartService.addProductToCart(productId, user.getId());
        return ok().build();
    }

    /**
     * Removes product from user's cart
     *
     * @param productId id of product
     * @param user      current user
     * @return http status ok
     */
    @DeleteMapping("/products/{productId}")
    public ResponseEntity<Void> removeProductFromCart(@PathVariable("productId") Integer productId,
                                                @RequestParam(value = "removeAll", required = false) Boolean removeAll,
                                                @AuthenticationPrincipal CustomUserDetails user) {
        cartService.removeProductFromCart(productId, user.getId(), removeAll);
        return ok().build();
    }

    /**
     * Clears user's cart
     *
     * @return http status OK
     */
    @DeleteMapping("/")
    public ResponseEntity<Void> clearCart(@AuthenticationPrincipal CustomUserDetails user) {
        cartService.clearCart(user.getId());
        return ok().build();
    }

    /**
     * Gets total price of order
     *
     * @param user current user
     * @return total price
     */
    @GetMapping("/total")
    public ResponseEntity<?> getTotalPrice(@AuthenticationPrincipal CustomUserDetails user) {
        return ok().body(cartService.getTotalPrice(user.getId()));
    }

    /**
     * Returns identifiers of product in cart
     *
     * @param user current user
     * @return list of product ids
     */
    @GetMapping("/products")
    public ResponseEntity<?> getProductInCartIds(@AuthenticationPrincipal CustomUserDetails user) {
        return ok().body(cartService.getProductInCartIds(user.getId()));
    }

}
