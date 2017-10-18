package ru.practice.kostin.shop.service.dto.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.practice.kostin.shop.persistence.entity.ProductEntity;
import ru.practice.kostin.shop.persistence.entity.ProductPhotoEntity;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class ProductFullDTO {
    private Integer id;
    private String name;
    private String description;
    private Double price;
    private List<String> photos;

    public ProductFullDTO(ProductEntity productEntity) {
        this.id = productEntity.getId();
        this.name = productEntity.getName();
        this.price = productEntity.getPrice().doubleValue();
        this.description = productEntity.getDescription();
        List<ProductPhotoEntity> photos = productEntity.getPhotoList();
        this.photos = photos.isEmpty() ? null :
                photos.stream()
                        .map(ProductPhotoEntity::getPath)
                        .collect(Collectors.toList());
    }
}
