package com.product.sell.sell.config;

import com.lly835.bestpay.config.WxPayH5Config;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class WechatPayConfig {
    @Autowired
    private WechatAccountPayConfig wechatAccountPayConfig;

    @Bean
    public BestPayServiceImpl bestPayService()
    {
        BestPayServiceImpl bestPayService = new BestPayServiceImpl();
        bestPayService.setWxPayH5Config(wxPayH5Config());

        return bestPayService;
    }

    @Bean
    public WxPayH5Config wxPayH5Config()
    {
        WxPayH5Config wxPayH5Config = new WxPayH5Config();
        wxPayH5Config.setAppId(wechatAccountPayConfig.getMpAppId());
        wxPayH5Config.setAppSecret(wechatAccountPayConfig.getMpAppSecret());
        wxPayH5Config.setKeyPath(wechatAccountPayConfig.getKeyPath());
        wxPayH5Config.setMchId(wechatAccountPayConfig.getMchId());
        wxPayH5Config.setMchKey(wechatAccountPayConfig.getMchKey());
        wxPayH5Config.setNotifyUrl(wechatAccountPayConfig.getNotifyUrl());

        return wxPayH5Config;
    }
}
