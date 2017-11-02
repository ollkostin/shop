package ru.practice.kostin.shop.service.dto.product;

import org.springframework.web.multipart.MultipartFile;

public class NewProductDTO extends ProductDTO {
    private String description;
    private MultipartFile[] photos;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MultipartFile[] getPhotos() {
        return photos;
    }

    public void setPhotos(MultipartFile[] photos) {
        this.photos = photos;
    }
}
