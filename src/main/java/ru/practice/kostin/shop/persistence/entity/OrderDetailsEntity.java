package ru.practice.kostin.shop.persistence.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "order_details")
@Data
public class OrderDetailsEntity {
    @EmbeddedId
    private OrderDetailsId id;
    private Integer count;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @MapsId("orderId")
    private OrderEntity order;

    @ManyToOne
    @MapsId("productId")
    private ProductEntity product;

    public OrderDetailsEntity(){
        this.id = new OrderDetailsId();
    }
}
