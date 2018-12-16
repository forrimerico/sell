package com.product.sell.sell.enums;

import lombok.Getter;

@Getter
public enum OrderStatus {
    NEW(0, "新订单"),
    FINISHED(1, "完结订单"),
    CANCEL(2, "订单取消")
    ;
    private Integer code;

    private String msg;

    OrderStatus(Integer code, String msg)
    {
        this.code = code;
        this.msg = msg;
    }
}
