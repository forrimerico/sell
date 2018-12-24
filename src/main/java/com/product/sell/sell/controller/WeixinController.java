/**
 * 调试类
 */

package com.product.sell.sell.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/weixin")
@Slf4j
public class WeixinController {

    @GetMapping("/auth")
    public void auth(@RequestParam("code") String code)
    {
        log.info("auth....");
        log.info("code = {}", code);

        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx1f7498d200c2b733&secret=388b216d50e20be05975550a59c3cdd4&code=" +
                code +
                "&grant_type=authorization_code";
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);
        log.info("response = {}", response);
    }
}
