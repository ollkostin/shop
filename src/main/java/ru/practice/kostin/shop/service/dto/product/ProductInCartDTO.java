package ru.practice.kostin.shop.service.dto.product;

import lombok.Getter;
import lombok.Setter;
import ru.practice.kostin.shop.persistence.entity.CartEntity;

@Getter
@Setter
public class ProductInCartDTO extends ProductShortDTO {
    private Integer count;

    public ProductInCartDTO(CartEntity cartEntity) {
        super(cartEntity.getProduct());
        this.count = cartEntity.getCount();
    }

}
