package com.product.sell.sell.aspect;

import com.product.sell.sell.enums.ResultEnum;
import com.product.sell.sell.exception.MyLoginException;
import com.product.sell.sell.exception.SellException;
import com.product.sell.sell.util.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Component
@Aspect
@Slf4j
public class SellerAuthorizeAspect {
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Pointcut("execution(public * com.product.sell.sell.controller.Sell*.*(..))")
    public void verify()
    { }

    @Before("verify()")
    public void doVerify()
    {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        // 查询cookie
        Cookie cookie = CookieUtil.getCookie(request, "token");
        if (cookie == null) {
            log.info("cookie中没有token");
            throw new MyLoginException();
        }
        String key = "token_" + cookie.getValue();
        String tokenValue = stringRedisTemplate.opsForValue().get(key);
        if (StringUtils.isEmpty(tokenValue)) {
            log.info("redis中不存在该key：{}", key);
            throw new MyLoginException();
        }
    }
}
