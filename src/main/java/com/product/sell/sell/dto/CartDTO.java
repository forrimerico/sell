package com.product.sell.sell.dto;

import lombok.Data;

/**
 * 购物车 DTO
 */
@Data
public class CartDTO {
    private String productId;

    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity)
    {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
