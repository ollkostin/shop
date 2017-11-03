package ru.practice.kostin.shop.persistence.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "product_photo")
@Data
public class ProductPhotoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String path;
    @ManyToOne
    private ProductEntity product;
}
