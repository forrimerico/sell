package com.product.sell.sell.service.impl;

import com.product.sell.sell.dao.OrderDetail;
import com.product.sell.sell.dto.OrderDTO;
import com.product.sell.sell.enums.OrderStatus;
import com.product.sell.sell.enums.PayStatus;
import com.product.sell.sell.util.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.AssertTrue;
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
        String orderId = "154494950704674973";
        OrderDTO result = orderService.findOne(orderId);
        log.info("查询单个订单", result);
        Assert.assertNotNull(result);
    }

    @Test
    public void findList() {
        String openId = "123456";
        Page<OrderDTO> result = orderService.findList(openId, new PageRequest(0 ,3));
        Assert.assertNotEquals(0, result.getSize());
    }

    @Test
    @Transactional
    public void cancle() {
        String orderId = "154494950704674973";
        OrderDTO result = orderService.findOne(orderId);
        OrderDTO cancleResult = orderService.cancle(result);
        Assert.assertEquals(OrderStatus.CANCEL.getCode(), cancleResult.getOrderStatus());
    }

    @Test
    @Transactional
    public void finish() {
        String orderId = "154494950704674973";
        OrderDTO result = orderService.findOne(orderId);
        OrderDTO cancleResult = orderService.finish(result);
        Assert.assertEquals(OrderStatus.FINISHED.getCode(), cancleResult.getOrderStatus());
    }

    @Test
    @Transactional
    public void paid() {
        String orderId = "154494950704674973";
        OrderDTO result = orderService.findOne(orderId);
        OrderDTO cancleResult = orderService.paid(result);
        Assert.assertEquals(PayStatus.SUCCESS.getCode(), cancleResult.getOrderStatus());
    }

    @Test
    public void list()
    {
        Page<OrderDTO> orderDTOList = orderService.findList(new PageRequest(0 ,3));
//        Assert.assertNotEquals(0, orderDTOList.getTotalElements());
        Assert.assertTrue("查询所有订单", orderDTOList.getTotalElements() > 0);
    }
}