package ru.practice.kostin.shop.persistence.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "\"order\"")
@Data
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    private String address;
    private BigDecimal totalPrice;
    @ManyToOne
    private UserEntity user;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order", cascade = CascadeType.PERSIST)
    private List<OrderDetailsEntity> orderDetails;
}
