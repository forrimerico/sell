package com.product.sell.sell.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {
    PRODUCT_NOT_EXIST(10, "商品不存在"),
    PRODUCT_STOCK_ERR(11, "商品库存不足")
    ;

    private Integer code;
    private String msg;
    ResultEnum(Integer code, String msg)
    {
        this.code = code;
        this.msg = msg;
    }
}
