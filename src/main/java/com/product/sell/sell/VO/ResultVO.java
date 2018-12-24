package com.product.sell.sell.VO;

import lombok.Data;

import java.util.ArrayList;

@Data
public class ResultVO<T> {

    private Integer code;

    private String message;

    private T data;
}
