package ru.practice.kostin.shop.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.practice.kostin.shop.persistence.entity.CartEntity;
import ru.practice.kostin.shop.persistence.entity.CartId;

import java.math.BigDecimal;

public interface CartRepository extends JpaRepository<CartEntity, CartId>, JpaSpecificationExecutor<CartEntity> {

    @Modifying
    @Query("delete from CartEntity c where c.id.userId=:userId")
    void deleteAllByUserId(@Param("userId") Integer userId);

    @Query("select SUM(c.product.price * c.count) from CartEntity c where c.id.userId=:userId and c.product.id = c.id.productId")
    BigDecimal getTotalPrice(@Param("userId") Integer userId);

}
