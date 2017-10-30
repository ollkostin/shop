package ru.practice.kostin.shop.service.dto.product;

import ru.practice.kostin.shop.persistence.entity.ProductEntity;
import ru.practice.kostin.shop.persistence.entity.ProductPhotoEntity;

import java.util.List;

public class ProductShortDTO extends ProductDTO {
    private String pathToPhoto;

    public ProductShortDTO(ProductEntity productEntity) {
        super(productEntity);
        List<ProductPhotoEntity> photos = productEntity.getPhotoList();
        this.pathToPhoto = photos.isEmpty() ? null : photos.get(0).getPath();
    }

    public String getPathToPhoto() {
        return pathToPhoto;
    }

    public void setPathToPhoto(String pathToPhoto) {
        this.pathToPhoto = pathToPhoto;
    }
}
