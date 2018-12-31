package com.product.sell.sell.enums;

import lombok.Getter;

@Getter
public enum PayStatus implements CodeEnum{

    WAIT(0, "等待支付"),
    SUCCESS(1, "支付成功")
    ;

    private Integer code;
    private String msg;

    PayStatus(Integer code, String msg)
    {
        this.code = code;
        this.msg = msg;
    }
}
