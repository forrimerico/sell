package com.product.sell.sell.service.impl;

import com.product.sell.sell.dto.OrderDTO;
import com.product.sell.sell.enums.ResultEnum;
import com.product.sell.sell.exception.SellException;
import com.product.sell.sell.service.BuyerService;
import com.product.sell.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BuyerServiceImpl implements BuyerService {
    @Autowired
    OrderService orderService;
    @Override
    public OrderDTO findOrderOne(String openid, String orderid) {
        OrderDTO orderDTO = orderService.findOne(orderid);
        if (orderDTO == null) {
            return null;
        }
        if (orderDTO.getBuyerOpenid().equalsIgnoreCase(openid)) {
            log.error("【查询订单】OPENID不匹配");
            throw new SellException(ResultEnum.PARAM_ERR);
        }

        return orderDTO;
    }

    @Override
    public OrderDTO cancleOrder(String openid, String orderid) {
        OrderDTO orderDTO = orderService.findOne(orderid);
        if (orderDTO == null) {
            log.error("【关闭订单】订单不存在！");
            throw new SellException(ResultEnum.PARAM_ERR);
        }
        if (orderDTO.getBuyerOpenid().equalsIgnoreCase(openid)) {
            log.error("【关闭订单】OPENID不匹配");
            throw new SellException(ResultEnum.PARAM_ERR);
        }
        OrderDTO result = orderService.cancle(orderDTO);

        return result;
    }
}
