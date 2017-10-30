package ru.practice.kostin.shop.service.dto.product;

import ru.practice.kostin.shop.persistence.entity.CartEntity;

public class ProductInCartDTO extends ProductShortDTO {
    private Integer count;

    public ProductInCartDTO(CartEntity cartEntity) {
        super(cartEntity.getProduct());
        this.count = cartEntity.getCount();
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
