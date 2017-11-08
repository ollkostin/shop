package ru.practice.kostin.shop.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.practice.kostin.shop.persistence.entity.ProductEntity;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

    @Query("select od.product from OrderDetailsEntity od inner join od.product where od.id.productId = :id")
    List<ProductEntity> findProductInOrderDetails(@Param("id") Integer id);
}
