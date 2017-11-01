package ru.practice.kostin.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.practice.kostin.shop.exception.FileTooLargeException;
import ru.practice.kostin.shop.exception.UnsupportedExtensionException;
import ru.practice.kostin.shop.persistence.entity.ProductEntity;
import ru.practice.kostin.shop.persistence.entity.ProductPhotoEntity;
import ru.practice.kostin.shop.persistence.repository.ProductPhotoRepository;
import ru.practice.kostin.shop.persistence.repository.ProductRepository;
import ru.practice.kostin.shop.service.dto.product.NewProductDTO;

import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigDecimal;

@Service
public class CreateProductService {
    private ProductRepository productRepository;
    private ProductPhotoRepository productPhotoRepository;
    private FileService fileService;


    @Transactional
    public ProductEntity createProduct(NewProductDTO productDTO) throws UnsupportedExtensionException, FileTooLargeException, IllegalArgumentException, IOException {
        validateNewProductDto(productDTO);
        ProductEntity product = new ProductEntity();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(BigDecimal.valueOf(productDTO.getPrice()));
        productRepository.save(product);
        if (productDTO.getPhoto() != null) {
            Integer id = product.getId();
            String fileName = fileService.saveFile(productDTO.getPhoto(), id);
            if (fileName != null) {
                ProductPhotoEntity photo = new ProductPhotoEntity();
                photo.setProduct(product);
                photo.setPath(fileName);
                productPhotoRepository.save(photo);
            }
        }
        return product;
    }

    private void validateNewProductDto(NewProductDTO productDTO) throws IllegalArgumentException {
        if (productDTO.getName().isEmpty() || productDTO.getName().length() > 255) {
            throw new IllegalArgumentException("name");
        }
        if (productDTO.getDescription().isEmpty() || productDTO.getDescription().length() > 2000) {
            throw new IllegalArgumentException("description");
        }
        if (productDTO.getPrice() == null) {
            throw new IllegalArgumentException("price");
        }
    }

    @Autowired
    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Autowired
    public void setProductPhotoRepository(ProductPhotoRepository productPhotoRepository) {
        this.productPhotoRepository = productPhotoRepository;
    }
}
