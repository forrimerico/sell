package com.product.sell.sell.controller;

import com.product.sell.sell.VO.ResultVO;
import com.product.sell.sell.enums.LoginEnum;
import com.product.sell.sell.enums.PayStatus;
import com.product.sell.sell.enums.ResultEnum;
import com.product.sell.sell.service.LoginService;
import com.product.sell.sell.util.CookieUtil;
import com.product.sell.sell.util.EnumUtil;
import com.product.sell.sell.util.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping("admin")
public class LoginController {

    @Autowired
    LoginService loginService;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @GetMapping("login/index")
    public ModelAndView loginPage()
    {
        return new ModelAndView("order/login");
    }

    @PostMapping("login/verify")
    public ResultVO loginVerify(@RequestParam("username") String username,
                                @RequestParam("password") String password,
                                HttpServletResponse httpServletResponse)
    {
        Integer result = loginService.verify(username, password, httpServletResponse);

        return ResultVOUtil.error(result, EnumUtil.getByCode(result, LoginEnum.class).getMsg());
    }

    @GetMapping("logout")
    public ModelAndView logout(HttpServletResponse httpServletResponse,
                       HttpServletRequest httpServletRequest,
                       Map<String, Object> map)
    {
        // 从cookie中查询
        Cookie cookie = CookieUtil.getCookie(httpServletRequest, "token");
        if (cookie != null) {
            String token = "token_" + cookie.getValue();
            stringRedisTemplate.opsForValue().getOperations().delete(token);
            httpServletResponse.addCookie(CookieUtil.setCookie("token", "", 0));
        }
        map.put("msg", "登出成功");
        map.put("url", "/sell/admin/login/index");
        return new ModelAndView("common/success", map);
    }
}
