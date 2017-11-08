package ru.practice.kostin.shop.service;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.practice.kostin.shop.exception.NotAllowedException;
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
public class CartService {
    private CartRepository cartRepository;
    private ProductRepository productRepository;

    /**
     * Gets cartByUserIdPredicate by user id
     *
     * @param userId user id
     * @return list of product DTOs
     */
    @Transactional
    public Page<ProductInCartDTO> getProductsInCart(Integer userId, Pageable pageable) {
        return getCart(userId, pageable).map(ProductInCartDTO::new);
    }

    /**
     * Adds product by id to cartByUserIdPredicate
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
     * Removes product copy from cartByUserIdPredicate by id.
     * If removeAllCopies is true, removes all copies.
     *
     * @param productId       product id
     * @param userId          user id
     * @param removeAllCopies flag  remove all copies
     * @throws NotAllowedException no product in cartByUserIdPredicate
     */
    @Transactional
    public void removeProductFromCart(Integer productId, Integer userId, Boolean removeAllCopies) throws NotAllowedException {
        CartId cartId = new CartId(userId, productId);
        CartEntity cart = cartRepository.findOne(cartId);
        if (cart == null) {
            throw new NotAllowedException("Your cartByUserIdPredicate does not contain this product");
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
     * Clears cartByUserIdPredicate
     *
     * @param userId user id
     */
    @Transactional
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
    @Transactional
    public Page<CartEntity> getCart(Integer userId, Pageable pageable) {
        return cartRepository.findAll((root, query, cb) -> cartByUserIdPredicate(root, query, cb, userId), pageable);
    }


    /**
     * Gets cartByUserIdPredicate
     *
     * @param userId user id
     * @return list of product entities
     */
    @Transactional
    public List<CartEntity> getCart(Integer userId) {
        return cartRepository.findAll((root, query, cb) -> cartByUserIdPredicate(root, query, cb, userId));
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

    @Autowired
    public void setCartRepository(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
}
