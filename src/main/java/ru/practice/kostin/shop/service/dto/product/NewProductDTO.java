package ru.practice.kostin.shop.service.dto.product;

import org.springframework.web.multipart.MultipartFile;

public class NewProductDTO extends ProductDTO {
    private String description;
    private MultipartFile photo;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MultipartFile getPhoto() {
        return photo;
    }

    public void setPhoto(MultipartFile photo) {
        this.photo = photo;
    }
}
