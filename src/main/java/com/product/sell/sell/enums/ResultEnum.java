package com.product.sell.sell.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {
    SUCCESS(0, "成功！"),
    PRODUCT_NOT_EXIST(10, "商品不存在"),
    PRODUCT_STOCK_ERR(11, "商品库存不足"),
    ORDER_MASTER_ERR(12, "订单不存在"),
    ORDER_DETAIL_ERR(13, "订单详情不存在"),
    ORDER_STATUS_ERR(14, "订单状态错误"),
    UPDATE_STATUS_ERR(15, "订单状态更新失败"),
    ORDER_DETAIL_EMPTY(16, "订单详情不存在"),
    PAY_STATUS_ERR(17, "订单支付状态错误"),
    PARAM_ERR(1, "参数不正确"),
    CART_EMPTY(18, "购物车为空"),
    WX_MP_ERROR(19, "微信公众账号错误"),
    WXPAY_NOTIFY_MONEY_VERIFY_ERR(20, "微信支付异步通知金额校验不通过"),
    USER_NOT_EXIST(21, "用户不存在"),
    USER_PWD_ERROR(22, "用户密码错误"),
    COOKIE_NOT_EXITS(23, "cookie不存在"),
    REDIS_NOT_EXITS(24, "redis中查不到token")
    ;

    private Integer code;
    private String msg;
    ResultEnum(Integer code, String msg)
    {
        this.code = code;
        this.msg = msg;
    }
}
