package com.product.sell.sell.util;

import java.util.Random;

public class KeyUtil {

    /**
     * 生成唯一主键
     * 时间 + 随机数
     * 防止多线程过程中生成重复键。
     * 因此需要加上 synchronized 同步关键字
     * @return
     */
    public static synchronized String generateKey()
    {
        Random random = new Random();

        Integer randomNum = random.nextInt(90000) + 10000;
        return System.currentTimeMillis() + String.valueOf(randomNum);

    }
}
