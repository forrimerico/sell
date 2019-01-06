package com.product.sell.sell.repositories;

import com.product.sell.sell.dao.SellerInfo;
import com.product.sell.sell.util.KeyUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerInfoRepositoryTest {
    @Autowired
    SellerInfoRepository sellerInfoRepository;

    @Test public void save()
    {
        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setId(KeyUtil.generateKey());
        sellerInfo.setOpenid("123");
        sellerInfo.setPassword("123");
        sellerInfo.setUsername("admin");
        sellerInfoRepository.save(sellerInfo);

    }

    @Test
    public void findByOpenid()
    {
        String openid = "123";
        SellerInfo sellerInfo = sellerInfoRepository.findByOpenid(openid);
        Assert.assertNotEquals(null, sellerInfo);
    }
}