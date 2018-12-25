package com.product.sell.sell.service;

import com.product.sell.sell.dto.OrderDTO;

public interface PayService {

    void create(OrderDTO orderDTO);
}
