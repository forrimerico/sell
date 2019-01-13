package com.product.sell.sell.service;

import lombok.extern.slf4j.Slf4j;
import org.simpleframework.xml.core.Commit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@Slf4j
public class RedisLock {
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    /**
     * 需要一个超时时间
     * @param key
     * @param value 当前时间 加上 超时时间
     * @return
     */
    public boolean lock(String key, String value)
    {
        // 如果返回1 则说明这个key原先没有设置过
        // 并且现在已经设置
        // 即 这个 key  被锁住了。
        if (stringRedisTemplate.opsForValue().setIfAbsent(key, value)) {
            return true;
        }

        // 如果逻辑到了这里，说明原来那个 key 被锁过，不能再锁了。
        // 但是为了防止死锁，这里做一下过期判断。
        // 获取到原来那个key的value（时间戳），这个时间戳如果小于当前时间（说明过期了）
        String currentTime = stringRedisTemplate.opsForValue().get(key);

        // 如果redis存的时间非空，并且小于当前时间
        // 进入到这里面的逻辑去
        if (!StringUtils.isEmpty(currentTime) &&
                Long.parseLong(currentTime) < System.currentTimeMillis()) {
            // 获取原来的value值（肯定等于currentTime）并且设置为新的值。
            // 并且返回true
            // 这里是防止两个线程同时访问这个方法的时候，先后顺序的问题。
            String oldValue = stringRedisTemplate.opsForValue().getAndSet(key, value);
            if (!StringUtils.isEmpty(oldValue) && oldValue.equals(currentTime)) {
                return true;
            }
        }

        return false;
    }

    public void unlock(String key, String value)
    {
        try {
            String currentTime = stringRedisTemplate.opsForValue().get(key);
            if (!StringUtils.isEmpty(currentTime) && currentTime.equals(value)) {
                stringRedisTemplate.opsForValue().getOperations().delete(key);
            }
        } catch (RuntimeException e) {
            log.error("分布式锁异常{}", e);
            e.printStackTrace();
        }
    }
}
