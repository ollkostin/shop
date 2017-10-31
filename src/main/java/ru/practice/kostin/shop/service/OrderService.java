package ru.practice.kostin.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.practice.kostin.shop.persistence.entity.*;
import ru.practice.kostin.shop.persistence.repository.OrderRepository;
import ru.practice.kostin.shop.persistence.repository.UserRepository;
import ru.practice.kostin.shop.service.dto.OrderDto;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    private final int ADDRESS_LENGTH = 300;

    private CartService cartService;
    private OrderRepository orderRepository;
    private UserRepository userRepository;

    @Transactional
    public void createOrder(Integer userId, OrderDto orderDto) throws IllegalArgumentException {
        if (orderDto.getAddress().isEmpty() || orderDto.getAddress().length() > ADDRESS_LENGTH) {
            throw new IllegalArgumentException("address field is wrong");
        }
        UserEntity userEntity = userRepository.findOne(userId);

        OrderEntity order = new OrderEntity();
        order.setDate(Date.valueOf(LocalDate.now()));
        order.setAddress(orderDto.getAddress());
        order.setTotalPrice(BigDecimal.valueOf(orderDto.getTotalPrice()));
        order.setUser(userEntity);

        List<CartEntity> cartEntityList = cartService.getCart(userId);
        List<OrderDetailsEntity> orderDetailsList  = new ArrayList<>();
        for (CartEntity productInCart : cartEntityList) {
            OrderDetailsEntity orderDetails = new OrderDetailsEntity();
            orderDetails.setId(new OrderDetailsId());
            orderDetails.setCount(productInCart.getCount());
            orderDetails.setProduct(productInCart.getProduct());
            orderDetails.setOrder(order);
            orderDetailsList.add(orderDetails);
        }
        order.setOrderDetails(orderDetailsList);
        orderRepository.save(order);
    }

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setCartService(CartService cartService) {
        this.cartService = cartService;
    }
}
