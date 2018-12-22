package com.product.sell.sell.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.product.sell.sell.dao.OrderDetail;
import com.product.sell.sell.enums.OrderStatus;
import com.product.sell.sell.enums.PayStatus;
import com.product.sell.sell.util.serializer.DateToLongSerializer;
import lombok.Data;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
//@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
// 上面这个废弃了。
// 如果结果中有null，则不去掉这个属性
// 还可以在yml里做全局配置.jackson ---
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO {
    private String orderId;

    private String buyerName;

    private String buyerPhone;

    private String buyerAddress;

    private String buyerOpenid;

    private BigDecimal orderAmount;

    private Integer orderStatus;

    private Integer payStatus;

    // 通过定义一个工具类，DateToLongSerializer 实现了Date -> Long 的转变。非常神奇
    @JsonSerialize(using = DateToLongSerializer.class)
    private Date createTime;

    @JsonSerialize(using = DateToLongSerializer.class)
    private Date updateTime;

    private List<OrderDetail> orderDetailList;
}
