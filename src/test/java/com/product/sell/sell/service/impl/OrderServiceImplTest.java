package com.product.sell.sell.service.impl;

import com.product.sell.sell.dao.OrderDetail;
import com.product.sell.sell.dto.OrderDTO;
import com.product.sell.sell.util.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {
    @Autowired
    OrderServiceImpl orderService;

    @Test
    public void create() {
        OrderDTO orderDTO = new OrderDTO();
        List<OrderDetail> orderDetailList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setProductId("1");
            orderDetail.setProductQuantity(1);
            orderDetailList.add(orderDetail);
        }

        // orderMaster 的内容
        orderDTO.setBuyerAddress("address");
        orderDTO.setBuyerName("name");
        orderDTO.setBuyerOpenid("123456");
        orderDTO.setBuyerPhone("1234567");
        orderDTO.setOrderDetailList(orderDetailList);
        OrderDTO result = orderService.create(orderDTO);
        log.info("【创建订单：result = {}】", result);
        Assert.assertNotNull(result);
    }

    @Test
    public void findOne() {
    }

    @Test
    public void findList() {
    }

    @Test
    public void cancle() {
    }

    @Test
    public void finish() {
    }

    @Test
    public void paid() {
    }
}