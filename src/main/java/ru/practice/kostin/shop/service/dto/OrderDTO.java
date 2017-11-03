package ru.practice.kostin.shop.service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDTO {
    private Double totalPrice;
    private String address;
}
