package ru.practice.kostin.shop.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practice.kostin.shop.persistence.entity.OrderDetailsEntity;
import ru.practice.kostin.shop.persistence.entity.OrderDetailsId;

public interface OrderDetailsRepository extends JpaRepository<OrderDetailsEntity, OrderDetailsId> {
}
