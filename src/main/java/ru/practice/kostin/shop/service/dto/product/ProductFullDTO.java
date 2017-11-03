package ru.practice.kostin.shop.service.dto.product;

import lombok.Getter;
import lombok.Setter;
import ru.practice.kostin.shop.persistence.entity.ProductEntity;
import ru.practice.kostin.shop.persistence.entity.ProductPhotoEntity;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ProductFullDTO extends ProductDTO {
    private String description;
    private List<String> photos;

    public ProductFullDTO(ProductEntity productEntity) {
        super(productEntity);
        this.description = productEntity.getDescription();
        List<ProductPhotoEntity> photos = productEntity.getPhotoList();
        this.photos = photos.isEmpty() ? null :
                photos.stream()
                        .map(ProductPhotoEntity::getPath)
                        .collect(Collectors.toList());
    }
}
