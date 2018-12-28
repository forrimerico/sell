package com.product.sell.sell.service;

import com.lly835.bestpay.model.PayResponse;
import com.product.sell.sell.dto.OrderDTO;

public interface PayService {

    PayResponse create(OrderDTO orderDTO);
}
