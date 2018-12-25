package com.product.sell.sell.service.impl;

import com.product.sell.sell.dto.OrderDTO;
import com.product.sell.sell.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.criterion.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class PayServiceImplTest {

    @Autowired
    private PayService payService;

    @Autowired
    OrderServiceImpl orderService;

    @Test
    public void create() {
        String orderId = "154494950704674973";
        OrderDTO result = orderService.findOne(orderId);
        payService.create(result);
    }
}