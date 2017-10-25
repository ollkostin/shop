package ru.practice.kostin.shop.service.dto.product;

import lombok.Getter;
import lombok.Setter;
import ru.practice.kostin.shop.persistence.entity.CartEntity;
import ru.practice.kostin.shop.persistence.entity.ProductEntity;
import ru.practice.kostin.shop.persistence.entity.ProductPhotoEntity;

import java.util.List;

@Getter
@Setter
public class ProductShortDTO {
    private Integer id;
    private String name;
    private Double price;
    private String pathToPhoto;
    private Integer count;

    public ProductShortDTO(ProductEntity productEntity) {
        this.id = productEntity.getId();
        this.name = productEntity.getName();
        this.price = productEntity.getPrice().doubleValue();
        List<ProductPhotoEntity> photos = productEntity.getPhotoList();
        this.pathToPhoto = photos.isEmpty() ? null : photos.get(0).getPath();
    }

    public ProductShortDTO(CartEntity cartEntity) {
        this(cartEntity.getProduct());
        this.count = cartEntity.getCount();
    }

    public ProductShortDTO() {
    }
}
