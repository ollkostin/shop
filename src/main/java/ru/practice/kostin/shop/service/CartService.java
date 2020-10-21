package ru.practice.kostin.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.practice.kostin.shop.exception.NotAllowedException;
import ru.practice.kostin.shop.exception.NotFoundException;
import ru.practice.kostin.shop.persistence.entity.CartEntity;
import ru.practice.kostin.shop.persistence.entity.CartId;
import ru.practice.kostin.shop.persistence.entity.ProductEntity;
import ru.practice.kostin.shop.persistence.repository.CartRepository;
import ru.practice.kostin.shop.persistence.repository.ProductRepository;
import ru.practice.kostin.shop.service.dto.product.ProductInCartDTO;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    /**
     * Gets cartByUserIdPredicate by user id
     *
     * @param userId user id
     * @return list of product DTOs
     */
    public Page<ProductInCartDTO> getProductsInCart(Integer userId, Pageable pageable) {
        return getCart(userId, pageable).map(ProductInCartDTO::new);
    }

    /**
     * Adds product by id to cartByUserIdPredicate
     *
     * @param productId product id
     * @param userId    user id
     */
    public void addProductToCart(Integer productId, Integer userId) {
        ProductEntity productEntity = productRepository
                .findById(productId)
                .orElseThrow(() -> new NotFoundException(String.format("Product with id:%d was not found", productId)));
        CartId cartId = new CartId(userId, productId);
        CartEntity cart = cartRepository
                .findById(cartId)
                .orElse(emptyCart(productEntity, cartId));
        Integer count = Optional.ofNullable(cart.getCount()).orElse(0);
        cart.setCount(count + 1);
        cartRepository.save(cart);
    }

    private CartEntity emptyCart(ProductEntity productEntity, CartId cartId) {
        CartEntity newCart = new CartEntity();
        newCart.setId(cartId);
        newCart.setProduct(productEntity);
        return newCart;
    }

    /**
     * Removes product copy from cartByUserIdPredicate by id.
     * If removeAllCopies is true, removes all copies.
     *
     * @param productId       product id
     * @param userId          user id
     * @param removeAllCopies flag  remove all copies
     */
    public void removeProductFromCart(Integer productId, Integer userId, Boolean removeAllCopies) {
        CartId cartId = new CartId(userId, productId);
        CartEntity cart = cartRepository
                .findById(cartId)
                .orElseThrow(() -> new NotAllowedException("Your cart does not contain this product"));
        Optional<Boolean> oRemoveAllCopies = Optional.ofNullable(removeAllCopies);
        if ((oRemoveAllCopies.isPresent() && oRemoveAllCopies.get()) || cart.getCount() == 1) {
            cartRepository.deleteById(cartId);
        } else {
            int count = cart.getCount();
            cart.setCount(count - 1);
            cartRepository.save(cart);
        }
    }

    /**
     * Clears cartByUserIdPredicate
     *
     * @param userId user id
     */
    public void clearCart(Integer userId) {
        cartRepository.deleteAllByUserId(userId);
    }

    /**
     * Gets cartByUserIdPredicate
     *
     * @param userId   user id
     * @param pageable pagination parameters
     * @return list of product entities
     */
    public Page<CartEntity> getCart(Integer userId, Pageable pageable) {
        return cartRepository.findAll(getCartEntitySpecification(userId), pageable);
    }


    /**
     * Gets cartByUserIdPredicate
     *
     * @param userId user id
     * @return list of product entities
     */
    @Transactional
    public List<CartEntity> getCart(Integer userId) {
        return cartRepository.findAll(getCartEntitySpecification(userId));
    }

    private Specification<CartEntity> getCartEntitySpecification(Integer userId) {
        return (root, query, cb) -> cartByUserIdPredicate(root, query, cb, userId);
    }

    /**
     * Gets total price of order
     *
     * @param userId user id
     * @return total price
     */
    public Double getTotalPrice(Integer userId) {
        return cartRepository.getTotalPrice(userId).doubleValue();
    }

    /**
     * Predicate to find cart by user id
     *
     * @param root   root
     * @param query  query
     * @param cb     criteria builder
     * @param userId user id
     * @return predicate
     */
    private Predicate cartByUserIdPredicate(Root<CartEntity> root, CriteriaQuery query, CriteriaBuilder cb, Integer userId) {
        root.join("id");
        query.orderBy(cb.asc(root.get("product")));
        return cb.equal(root.get("id").get("userId"), userId);
    }

    /**
     * Returns identifiers of product in cart
     *
     * @param userId user id
     * @return list of product ids
     */
    public List<Integer> getProductInCartIds(Integer userId) {
        return getCart(userId)
                .stream()
                .map(CartEntity::getProduct)
                .map(ProductEntity::getId)
                .collect(Collectors.toList());
    }

}
