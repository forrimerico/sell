package com.product.sell.sell.repositories;

import com.product.sell.sell.dao.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {
    @Autowired
    OrderMasterRepository orderMasterRepository;

    private final String OPENID = "123456";

    @Test
    @Transactional
    public void save()
    {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setBuyerAddress("hdu");
        orderMaster.setBuyerName("forri");
        orderMaster.setBuyerOpenid(OPENID);
        orderMaster.setBuyerPhone("12345678");
        orderMaster.setOrderAmount(new BigDecimal(1));
        orderMaster.setOrderId("123456");
        OrderMaster result = orderMasterRepository.save(orderMaster);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByBuyerOpenid()
    {
        PageRequest pageRequest = new PageRequest(0,2);
        Page<OrderMaster> result = orderMasterRepository.findByBuyerOpenid(OPENID, pageRequest);
        Assert.assertNotEquals(0, result.getTotalElements());
    }

}