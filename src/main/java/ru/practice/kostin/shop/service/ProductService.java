package ru.practice.kostin.shop.service;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.practice.kostin.shop.persistence.entity.ProductEntity;
import ru.practice.kostin.shop.persistence.repository.ProductRepository;
import ru.practice.kostin.shop.service.dto.product.ProductFullDTO;
import ru.practice.kostin.shop.service.dto.product.ProductShortDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private ProductRepository productRepository;

    /**
     * Returns list of products
     *
     * @return list of products
     */
    public List<ProductShortDTO> getProducts(Pageable pageable) {
        Page<ProductEntity> productEntities = productRepository.findAll(pageable);
        return productEntities.getContent()
                .stream()
                .map(ProductShortDTO::new)
                .collect(Collectors.toList());
    }

    /**
     * Returns product by id
     *
     * @param productId id of the product
     * @return product
     * @throws NotFoundException no product with specified id
     */
    public ProductFullDTO getProduct(Integer productId) throws NotFoundException {
        ProductEntity productEntity = productRepository.findOne(productId);
        if (productEntity == null) {
            throw new NotFoundException(String.format("Product with id:%d does not exist", productId));
        }
        return new ProductFullDTO(productEntity);
    }


    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
}
