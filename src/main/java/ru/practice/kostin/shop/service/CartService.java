package ru.practice.kostin.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.practice.kostin.shop.persistence.entity.CartEntity;
import ru.practice.kostin.shop.persistence.entity.ProductEntity;
import ru.practice.kostin.shop.persistence.repository.CartRepository;
import ru.practice.kostin.shop.service.dto.product.ProductShortDTO;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {
    private CartRepository cartRepository;

    public List<ProductShortDTO> getCart(Integer userId) {
        List<CartEntity> cart = (List<CartEntity>) cartRepository.findAll((root, query, cb) -> {
            Join<CartEntity, ProductEntity> productEntityJoin = root.join("id");
            return cb.equal(root.get("id").get("userId"), userId);
        });

         return cart.stream().map(ProductShortDTO::new).collect(Collectors.toList());
    }

    @Autowired
    public void setCartRepository(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }
}
