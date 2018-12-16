package com.product.sell.sell.exception;

import com.product.sell.sell.enums.ResultEnum;

public class SellException extends RuntimeException {
    private Integer code;

    public SellException(ResultEnum resultEnum)
    {
        // 父类构造方法（RuntimeException）需要一个msg来初始化
        super(resultEnum.getMsg());
        this.code=resultEnum.getCode();

    }
}
