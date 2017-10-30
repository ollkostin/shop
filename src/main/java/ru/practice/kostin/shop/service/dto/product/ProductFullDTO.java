package ru.practice.kostin.shop.service.dto.product;

import ru.practice.kostin.shop.persistence.entity.ProductEntity;
import ru.practice.kostin.shop.persistence.entity.ProductPhotoEntity;

import java.util.List;
import java.util.stream.Collectors;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }
}
