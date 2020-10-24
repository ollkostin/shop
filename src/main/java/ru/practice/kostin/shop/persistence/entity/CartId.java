package ru.practice.kostin.shop.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
public class CartId implements Serializable {
    private Integer userId;
    private Integer productId;
}
