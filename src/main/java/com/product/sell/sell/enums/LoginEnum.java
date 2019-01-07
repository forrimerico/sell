package com.product.sell.sell.enums;

import lombok.Getter;

@Getter
public enum LoginEnum implements CodeEnum {

    SUCCESS(0, "登录成功"),
    ERR_PWD(1, "密码错误"),
    ERR_USN(2, "账号不存在")
    ;

    private Integer code;
    private String msg;

    LoginEnum(Integer code, String msg)
    {
        this.code = code;
        this.msg = msg;
    }
}
