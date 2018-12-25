package com.product.sell.sell.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 通过下面的注解，可以实现从 yml中读取数据
 */
@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WechatAccountConfig {
    private String mpAppId;

    private String mpAppSecret;
    // 微信商户的一些配置信息
    private String mchId;

    private String mchKey;

    private String keyPath;

    private String notifyUrl;
}
