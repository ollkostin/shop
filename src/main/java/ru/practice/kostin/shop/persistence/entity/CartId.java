package ru.practice.kostin.shop.persistence.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CartId implements Serializable {
    private Integer userId;
    private Integer productId;

    public CartId(Integer userId, Integer productId) {
        this.userId = userId;
        this.productId = productId;
    }

    public CartId() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "CartId{" +
                "userId=" + userId +
                ", productId=" + productId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        CartId cartId = (CartId) o;

        if (!userId.equals(cartId.userId)) return false;
        return productId.equals(cartId.productId);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + userId.hashCode();
        result = 31 * result + productId.hashCode();
        return result;
    }
}
