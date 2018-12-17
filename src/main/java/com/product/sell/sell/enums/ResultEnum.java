package com.product.sell.sell.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {
    PRODUCT_NOT_EXIST(10, "商品不存在"),
    PRODUCT_STOCK_ERR(11, "商品库存不足"),
    ORDER_MASTER_ERR(12, "订单不存在"),
    ORDER_DETAIL_ERR(13, "订单详情不存在"),
    ;

    private Integer code;
    private String msg;
    ResultEnum(Integer code, String msg)
    {
        this.code = code;
        this.msg = msg;
    }
}
