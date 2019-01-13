package com.product.sell.sell.service.impl;

import com.product.sell.sell.exception.SellException;
import com.product.sell.sell.service.RedisLock;
import com.product.sell.sell.service.SellKillService;
import com.product.sell.sell.util.KeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SellKillServiceImpl implements SellKillService {
    @Autowired
    RedisLock redisLock;

    private static final int EXPIREDTIME = 100000;

    static Map<String, Integer> products;
    static Map<String, Integer> stock;
    static Map<String, String> orders;
    static {
        products = new HashMap<>();
        stock = new HashMap<>();
        orders = new HashMap<>();
        products.put("123456", 10000);
        stock.put("123456", 10000);
    }

    private String queryMap(String productId)
    {
        return "国庆期间，皮蛋粥限量" +
                products.get(productId) +
                "还剩下" +
                stock.get(productId)+
                "份" +
                "该商品成功下单用户数：" +
                orders.size();
    }

    @Override
    public synchronized String sellKillProduct(String productId) {
        // 加锁
        long time = System.currentTimeMillis() + EXPIREDTIME;

        boolean isLock = redisLock.lock(productId, String.valueOf(time));

        if (!isLock) {
            throw new SellException(101, "哎哟，人太多了，换个姿势再试试~");
        }
        int stockNum = stock.get(productId);
        if (stockNum == 0) {
            throw new SellException(100, "活动已经结束");
        } else {
            orders.put(KeyUtil.generateKey(), productId);
            stockNum = stockNum - 1;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            stock.put(productId, stockNum);
        }

        redisLock.unlock(productId, String.valueOf(time));
        return this.queryMap(productId);
    }
}
