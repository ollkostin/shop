package ru.practice.kostin.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.practice.kostin.shop.persistence.entity.ProductEntity;
import ru.practice.kostin.shop.persistence.entity.ProductPhotoEntity;
import ru.practice.kostin.shop.persistence.repository.ProductPhotoRepository;
import ru.practice.kostin.shop.persistence.repository.ProductRepository;
import ru.practice.kostin.shop.service.dto.product.NewProductDTO;
import ru.practice.kostin.shop.util.PhotoFileUtil;

import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

@Service
public class CreateProductService {
    private ProductRepository productRepository;
    private ProductPhotoRepository productPhotoRepository;
    private FileService fileService;


    /**
     * Saves product
     *
     * @param productDTO
     * @return map with errors
     * @throws IOException
     */
    @Transactional
    public HashMap<String, List<String>> createProduct(NewProductDTO productDTO) throws IOException {
        HashMap<String, List<String>> errors = validateNewProductDTO(productDTO);
        if (errors.isEmpty()) {
            ProductEntity product = new ProductEntity();
            product.setName(productDTO.getName());
            product.setDescription(productDTO.getDescription());
            product.setPrice(BigDecimal.valueOf(productDTO.getPrice()));
            productRepository.save(product);
            savePhotos(product, productDTO.getPhotos());
        }
        return errors;
    }

    public HashMap<String, List<String>> validateNewProductDTO(NewProductDTO productDTO) {
        HashMap<String, List<String>> errors = new HashMap<>();
        if (productDTO.getName().isEmpty() || productDTO.getName().length() > 255) {
            errors.put("name", Collections.singletonList("Name can not be empty and contain less than 255 characters"));
        }
        if (productDTO.getDescription().isEmpty() || productDTO.getDescription().length() > 2000) {
            errors.put("description", Collections.singletonList("Description can not be empty and contain more than 2000 characters"));
        }
        if (productDTO.getPrice() == null || productDTO.getPrice() < 1) {
            errors.put("price", Collections.singletonList("Price can not be empty or less than zero"));
        }
        for (MultipartFile photo : productDTO.getPhotos()) {
            List<String> fileErrors = new ArrayList<>();
            String fileName = photo.getOriginalFilename();
            if (!PhotoFileUtil.fileHasImageExtension(photo)) {
                fileErrors.add("File \"" + fileName + "\" has not supported or not image extension " + Arrays.toString(PhotoFileUtil.getImageExtensions()));
            }
            if (!PhotoFileUtil.isSizeAllowed(photo)) {
                fileErrors.add("File \"" + fileName + "\" is larger than allowed size : ");
            }
            errors.put(fileName, fileErrors);
        }
        return errors;
    }

    public void savePhotos(ProductEntity product, MultipartFile[] photos) throws IOException {
        if (photos != null && photos.length > 0) {
            List<ProductPhotoEntity> productPhotoEntityList = new ArrayList<>();
            for (MultipartFile photo : photos) {
                String fileName = fileService.saveFile(photo, product.getId());
                if (fileName != null) {
                    ProductPhotoEntity photoEntity = new ProductPhotoEntity();
                    photoEntity.setProduct(product);
                    photoEntity.setPath(fileName);
                    productPhotoEntityList.add(photoEntity);
                }
            }
            productPhotoRepository.save(productPhotoEntityList);
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
