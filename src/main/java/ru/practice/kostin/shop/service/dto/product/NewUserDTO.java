package ru.practice.kostin.shop.service.dto.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewUserDTO {
    private String email;
    private String password;
    private String confirmPassword;
    private String role;
}
