package com.product.sell.sell.service.impl;

import com.product.sell.sell.dao.ProductCategory;
import com.product.sell.sell.dao.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoServiceImplTest {

    @Autowired
    ProductInfoServiceImpl productInfoService;

    @Test
    public void findOne() {
        String id = "123456";
        ProductInfo result = productInfoService.findOne(id);
        Assert.assertEquals("123456", result.getProductId());
    }

    @Test
    public void findAll() {
        PageRequest pageRequest = new PageRequest(0, 2);
        Page<ProductInfo> result = productInfoService.findAll(pageRequest);
        System.out.println(result.getTotalElements());
    }

    @Test
    public void findUp() {
        List<ProductInfo> result = productInfoService.findUp();
        Assert.assertNotNull(result);
    }

    @Test
    @Transactional
    public void save() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("123456");
        productInfo.setProductName("food 1");
        productInfo.setCategoryType(1);
        productInfo.setProductStatus(0);
        productInfo.setProductDescription("good");
        productInfo.setProductPrice(new BigDecimal(3.2));
        productInfo.setProductIcon("http://www.baidu.com");
        productInfo.setCategoryType(1);
        productInfo.setProductStock(100);

        ProductInfo result = productInfoService.save(productInfo);
        Assert.assertNotNull(result);
    }
}