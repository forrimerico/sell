package com.product.sell.sell.service.impl;

import com.product.sell.sell.constant.RedisConstant;
import com.product.sell.sell.dao.SellerInfo;
import com.product.sell.sell.enums.LoginEnum;
import com.product.sell.sell.enums.ResultEnum;
import com.product.sell.sell.exception.SellException;
import com.product.sell.sell.repositories.SellerInfoRepository;
import com.product.sell.sell.service.LoginService;
import com.product.sell.sell.util.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class LoginServiceImpl implements LoginService {

    @Autowired
    SellerInfoRepository sellerInfoRepository;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Override
    public Integer verify(String username, String password, HttpServletResponse httpServletResponse) {
        // 去数据库查
        SellerInfo sellerInfo = getUserByUname(username);
        if (sellerInfo == null) {
            log.error("用户不存在！{}", username);
            return LoginEnum.ERR_USN.getCode();
        }
        if (!password.equals(sellerInfo.getPassword())) {
            log.error("用户密码错误！{}");
            return LoginEnum.ERR_PWD.getCode();
        }
        // 设置token到redis
        String uuid = UUID.randomUUID().toString();
        String key = RedisConstant.PREFIX + uuid;
        Integer expired = RedisConstant.EXPIRED;
        stringRedisTemplate.opsForValue().set(key, password, expired, TimeUnit.SECONDS);
        // 设置 token 到cookie
        Cookie cookie = CookieUtil.setCookie("token", uuid, 7200);
        httpServletResponse.addCookie(cookie);

        return LoginEnum.SUCCESS.getCode();
    }

    private SellerInfo getUserByUname(String username)
    {
        return sellerInfoRepository.findByUsername(username);
    }
}
