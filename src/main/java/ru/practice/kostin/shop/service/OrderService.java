package ru.practice.kostin.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.practice.kostin.shop.persistence.entity.OrderEntity;
import ru.practice.kostin.shop.persistence.entity.UserEntity;
import ru.practice.kostin.shop.persistence.repository.OrderRepository;
import ru.practice.kostin.shop.persistence.repository.UserRepository;
import ru.practice.kostin.shop.service.dto.OrderDto;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;

@Service
public class OrderService {
    private final int ADDRESS_LENGTH = 300;
    private OrderRepository orderRepository;
    private UserRepository userRepository;

    @Transactional
    public void createOrder(Integer userId, OrderDto orderDto) {
        if (orderDto.getAddress().isEmpty() || orderDto.getAddress().length() > ADDRESS_LENGTH) {
            throw new IllegalArgumentException("address field is wrong");
        }
        UserEntity userEntity = userRepository.findOne(userId);

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setDate(Date.valueOf(LocalDate.now()));
        orderEntity.setAddress(orderDto.getAddress());
        orderEntity.setTotalPrice(BigDecimal.valueOf(orderDto.getTotalPrice()));
        orderEntity.setUser(userEntity);
        //TODO: очистить корзину, записать информацию о заказе
        orderRepository.save(orderEntity);
    }

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
