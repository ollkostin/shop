package ru.practice.kostin.shop.persistence.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "cart")
@Data
public class CartEntity {
    @EmbeddedId
    private CartId id;
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productId")
    private ProductEntity product;
    private Integer count;
}
