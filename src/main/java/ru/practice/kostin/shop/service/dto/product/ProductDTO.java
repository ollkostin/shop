package ru.practice.kostin.shop.service.dto.product;

import lombok.Getter;
import lombok.Setter;
import ru.practice.kostin.shop.persistence.entity.ProductEntity;

@Getter
@Setter
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
}
