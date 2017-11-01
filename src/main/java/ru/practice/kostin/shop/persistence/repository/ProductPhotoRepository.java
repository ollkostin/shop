package ru.practice.kostin.shop.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practice.kostin.shop.persistence.entity.ProductPhotoEntity;

public interface ProductPhotoRepository extends JpaRepository<ProductPhotoEntity, Integer> {
}
