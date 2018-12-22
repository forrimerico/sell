package com.product.sell.sell.service;

import com.product.sell.sell.dto.OrderDTO;

public interface BuyerService {
    OrderDTO findOrderOne(String openid, String orderid);

    OrderDTO cancleOrder(String openid, String orderid);
}
