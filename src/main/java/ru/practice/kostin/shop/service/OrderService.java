package ru.practice.kostin.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practice.kostin.shop.exception.NotFoundException;
import ru.practice.kostin.shop.persistence.entity.CartEntity;
import ru.practice.kostin.shop.persistence.entity.OrderDetailsEntity;
import ru.practice.kostin.shop.persistence.entity.OrderEntity;
import ru.practice.kostin.shop.persistence.entity.UserEntity;
import ru.practice.kostin.shop.persistence.repository.OrderRepository;
import ru.practice.kostin.shop.persistence.repository.UserRepository;
import ru.practice.kostin.shop.service.dto.OrderDTO;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private static final int ADDRESS_LENGTH = 300;

    private final CartService cartService;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    /**
     * Creates order for user
     *
     * @param userId   user id
     * @param orderDTO order information
     * @return order id
     */
    @Transactional
    public Integer createOrder(Integer userId, OrderDTO orderDTO) {
        validateOrderDTO(orderDTO);
        UserEntity userEntity = userRepository.findById(userId)
                                              .orElseThrow(() -> new NotFoundException("User with id " + userId + " was not found"));

        OrderEntity order = new OrderEntity();
        order.setDate(Date.from(Instant.now()));
        order.setAddress(orderDTO.getAddress());
        order.setUser(userEntity);

        List<CartEntity> cartEntityList = cartService.getCart(userId);
        List<OrderDetailsEntity> orderDetailsList = new ArrayList<>();
        for (CartEntity productInCart : cartEntityList) {
            OrderDetailsEntity orderDetails = new OrderDetailsEntity();
            orderDetails.setCount(productInCart.getCount());
            orderDetails.setProduct(productInCart.getProduct());
            orderDetails.setOrder(order);
            orderDetailsList.add(orderDetails);
        }
        order.setOrderDetails(orderDetailsList);
        orderRepository.save(order);
        return order.getId();
    }

    private void validateOrderDTO(OrderDTO orderDTO) {
        if (orderDTO.getAddress().isEmpty() || orderDTO.getAddress().length() > ADDRESS_LENGTH) {
            throw new IllegalArgumentException("Address cannot be empty or contain more than "
                    + ADDRESS_LENGTH + " characters");
        }
    }

}
