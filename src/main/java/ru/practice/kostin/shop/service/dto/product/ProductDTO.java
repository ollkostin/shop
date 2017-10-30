package ru.practice.kostin.shop.service.dto.product;

import ru.practice.kostin.shop.persistence.entity.ProductEntity;

public class ProductDTO {
    private Integer id;
    private String name;
    private Double price;

    public ProductDTO(ProductEntity productEntity) {
        this.id = productEntity.getId();
        this.name = productEntity.getName();
        this.price = productEntity.getPrice().doubleValue();
    }

    public ProductDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
