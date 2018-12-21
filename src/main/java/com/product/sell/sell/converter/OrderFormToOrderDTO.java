package com.product.sell.sell.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.product.sell.sell.dao.OrderDetail;
import com.product.sell.sell.dto.OrderDTO;
import com.product.sell.sell.enums.ResultEnum;
import com.product.sell.sell.exception.SellException;
import com.product.sell.sell.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class OrderFormToOrderDTO {

    public static OrderDTO convert(OrderForm orderForm)
    {
        Gson gson = new Gson();
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());
        // orderForm里的是json字符串。需要把json字符串转化成list
        // 还是json_encode 犀利。。。
        List<OrderDetail> orderDetailList = new ArrayList<>();
        try {
            orderDetailList = gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<OrderDetail>>(){}.getType());
        } catch (Exception e) {
            log.error("【对象转化出错】，string={}", orderForm.getItems());
            throw new SellException(ResultEnum.PARAM_ERR);
        }
        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }
}
