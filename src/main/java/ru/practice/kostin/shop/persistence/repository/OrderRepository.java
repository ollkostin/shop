package ru.practice.kostin.shop.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practice.kostin.shop.persistence.entity.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {
}
