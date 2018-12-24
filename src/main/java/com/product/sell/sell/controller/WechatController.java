package com.product.sell.sell.controller;

import com.product.sell.sell.enums.ResultEnum;
import com.product.sell.sell.exception.SellException;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLEncoder;

@Controller
@RequestMapping("/wechat")
@Slf4j
public class WechatController {

    @Autowired
    private WxMpService wxMpService;

    @GetMapping("authorize")
    public String authorize(@RequestParam("returnUrl") String url)
    {
        // 配置 通过 WechatMpconfig
        // 调用方法
        String urlJump = "http://forri.natapp1.cc/sell/wechat/userinfo";
        String redirectUrl = wxMpService.oauth2buildAuthorizationUrl(urlJump, WxConsts.OAUTH2_SCOPE_BASE, URLEncoder.encode(url));

        log.info("微信网页授权 result={}", redirectUrl);

        return "redirect:" + redirectUrl;
    }

    @GetMapping("/userinfo")
    public String userInfo(@RequestParam("code") String code,
                         @RequestParam("state") String returnUrl)
    {
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = new WxMpOAuth2AccessToken();
        try {
            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
        } catch (WxErrorException e) {
            log.error("微信网页授权失败！");
            throw new SellException(ResultEnum.WX_MP_ERROR.getCode(), e.getMessage());
        }

        String openId = wxMpOAuth2AccessToken.getAccessToken();

        return "redirect:" + returnUrl + "?openid="  + openId ;
    }
}
