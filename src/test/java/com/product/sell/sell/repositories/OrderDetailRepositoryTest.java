package com.product.sell.sell.repositories;

import com.product.sell.sell.dao.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {

    @Autowired
    OrderDetailRepository orderDetailRepository;
    @Test
    public void save()
    {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("234");
        orderDetail.setOrderId("123");
        orderDetail.setProductIcon("http://xxx.jpg");
        orderDetail.setProductId("12345");
        orderDetail.setProductName("test");
        orderDetail.setProductPrice(new BigDecimal(1.5));
        orderDetail.setProductQuantity(123);
        orderDetailRepository.save(orderDetail);
    }

    @Test
    public void findByOrderIdTest()
    {
        List<OrderDetail> result = orderDetailRepository.findByOrderId("123");
        Assert.assertNotEquals(0, result.size());
    }
}