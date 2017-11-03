package ru.practice.kostin.shop.service.dto.product;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class NewProductDTO extends ProductDTO {
    private String description;
    private MultipartFile[] photos;
}
