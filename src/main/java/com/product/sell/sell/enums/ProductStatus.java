package com.product.sell.sell.enums;

import lombok.Getter;

@Getter
public enum ProductStatus {

    UP(0, "上架"),
    DOWN(1, "下架")
    ;
    private Integer code;
    private String msg;

    ProductStatus(Integer code, String msg)
    {
        this.code = code;
        this.msg = msg;
    }
}
