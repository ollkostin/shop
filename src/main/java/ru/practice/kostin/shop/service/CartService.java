package ru.practice.kostin.shop.service;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.practice.kostin.shop.exception.NotAllowedException;
import ru.practice.kostin.shop.persistence.entity.CartEntity;
import ru.practice.kostin.shop.persistence.entity.CartId;
import ru.practice.kostin.shop.persistence.entity.ProductEntity;
import ru.practice.kostin.shop.persistence.repository.CartRepository;
import ru.practice.kostin.shop.persistence.repository.ProductRepository;
import ru.practice.kostin.shop.service.dto.product.ProductInCartDTO;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartService {
    private CartRepository cartRepository;
    private ProductRepository productRepository;

    /**
     * Gets cart by user id
     *
     * @param userId user id
     * @return list of product DTOs
     */
    @Transactional
    public List<ProductInCartDTO> getProductsInCart(Integer userId) {
        List<CartEntity> cart = getCart(userId);
        return cart
                .stream()
                .map(ProductInCartDTO::new)
                .collect(Collectors.toList());
    }

    /**
     * Adds product by id to cart
     *
     * @param productId product id
     * @param userId    user id
     * @throws NotFoundException product was not found
     */
    @Transactional
    public void addProductToCart(Integer productId, Integer userId) throws NotFoundException {
        ProductEntity productEntity = productRepository.findOne(productId);
        if (productEntity == null) {
            throw new NotFoundException(String.format("Product with id:%d was not found", productId));
        }
        CartId cartId = new CartId(userId, productId);
        CartEntity cart = cartRepository.findOne(cartId);
        if (cart == null) {
            cart = new CartEntity();
            cart.setId(cartId);
            cart.setProduct(productEntity);
        }
        Integer count = Optional.ofNullable(cart.getCount()).orElse(0);
        cart.setCount(count + 1);
        cartRepository.save(cart);
    }

    /**
     * Removes product copy from cart by id.
     * If removeAllCopies is true, removes all copies.
     *
     * @param productId       product id
     * @param userId          user id
     * @param removeAllCopies flag  remove all copies
     * @throws NotAllowedException no product in cart
     */
    @Transactional
    public void removeProductFromCart(Integer productId, Integer userId, Boolean removeAllCopies) throws NotAllowedException {
        CartId cartId = new CartId(userId, productId);
        CartEntity cart = cartRepository.findOne(cartId);
        if (cart == null) {
            throw new NotAllowedException("Your cart does not contain this product");
        }
        Optional<Boolean> oRemoveAllCopies = Optional.ofNullable(removeAllCopies);
        if ((oRemoveAllCopies.isPresent() && oRemoveAllCopies.get()) || cart.getCount() == 1) {
            cartRepository.delete(cartId);
        } else {
            int count = cart.getCount();
            cart.setCount(count - 1);
            cartRepository.save(cart);
        }
    }

    /**
     * Clears cart
     *
     * @param userId user id
     */
    @Transactional
    public void clearCart(Integer userId) {
        cartRepository.delete(getCart(userId));
    }

    /**
     * Gets cart
     *
     * @param userId user id
     * @return list of product entities
     */
    @Transactional
    public List<CartEntity> getCart(Integer userId) {
        return cartRepository.findAll((root, query, cb) -> {
            root.join("id");
            query.orderBy(cb.asc(root.get("product")));
            return cb.equal(root.get("id").get("userId"), userId);
        });
    }

    @Autowired
    public void setCartRepository(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
}
