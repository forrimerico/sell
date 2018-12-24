package com.product.sell.sell.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class HttpAspact {

    private final static Logger logger = LoggerFactory.getLogger(HttpAspact.class);

    @Pointcut("execution(public * com.product.sell.sell.controller.BuyerOrderController.list(..))")
    public void log(){}

    @Before("log()")
    public void doBefore(JoinPoint joinPoint)
    {
        ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // url
        logger.info("url={}", request.getRequestURL());

        // method
        logger.info("method={}", request.getMethod());

        // IP
        logger.info("ip={}", request.getRemoteAddr());

        // class 方法
        logger.info("class method = {}", joinPoint.getSignature().getDeclaringTypeName() +
                "." +
                 joinPoint.getSignature().getName());

        // 参数
        logger.info("args={}", joinPoint.getArgs());
    }

    @After("log()")
    public void doAfter()
    {

    }
    @AfterReturning(returning = "object", pointcut = "log()")
    public void doAfterReturning(Object object)
    {
        logger.info("response={}", object);
    }
}
