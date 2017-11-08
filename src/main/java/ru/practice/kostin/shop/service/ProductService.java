package ru.practice.kostin.shop.service;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.practice.kostin.shop.exception.NotAllowedException;
import ru.practice.kostin.shop.persistence.entity.ProductEntity;
import ru.practice.kostin.shop.persistence.repository.ProductRepository;
import ru.practice.kostin.shop.service.dto.product.ProductFullDTO;
import ru.practice.kostin.shop.service.dto.product.ProductShortDTO;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepository;

    /**
     * Returns list of products
     *
     * @return list of products
     */
    @Transactional
    public Page<ProductShortDTO> getProducts(Pageable pageable) {
        return productRepository.findAll(pageable).map(ProductShortDTO::new);
    }

    /**
     * Returns product by id
     *
     * @param productId id of the product
     * @return product
     * @throws NotFoundException no product with specified id
     */
    @Transactional
    public ProductFullDTO getProduct(Integer productId) throws NotFoundException {
        ProductEntity productEntity = productRepository.findOne(productId);
        if (productEntity == null) {
            throw new NotFoundException(String.format("Product with id:%d does not exist", productId));
        }
        return new ProductFullDTO(productEntity);
    }

    /**
     * Deletes product by id
     * @param productId product id
     * @throws NotFoundException if product was not found
     */
    @Transactional
    public void deleteProduct(Integer productId) throws NotFoundException, NotAllowedException {
        ProductEntity productEntity = productRepository.findOne(productId);
        if (productEntity == null) {
            throw new NotFoundException(String.format("Product with id:%d does not exist", productId));
        }
        List<ProductEntity> orderDetailsEntityList = productRepository.findProductInOrderDetails(productId);
        if (orderDetailsEntityList.isEmpty()) {
            productRepository.delete(productId);
        } else {
            throw new NotAllowedException("This product was already ordered, cannot delete");
        }
    }


    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
}
