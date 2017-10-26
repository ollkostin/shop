package ru.practice.kostin.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.practice.kostin.shop.security.CustomUserDetails;
import ru.practice.kostin.shop.service.OrderService;
import ru.practice.kostin.shop.service.dto.OrderDto;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    private OrderService orderService;

    @PostMapping("/")
    public ResponseEntity createOrder(@ModelAttribute("order") OrderDto orderDto) {
        CustomUserDetails user = (CustomUserDetails)
                (SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        orderService.createOrder(user.getId(), orderDto);
        return ok().build();
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }
}
