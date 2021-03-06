package ru.practice.kostin.shop.persistence.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "product")
@Data
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private BigDecimal price;
    private String description;
    private Boolean removed;
    @OneToMany(mappedBy = "product")
    private List<ProductPhotoEntity> photoList;
}
