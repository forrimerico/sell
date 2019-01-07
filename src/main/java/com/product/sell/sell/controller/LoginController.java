package com.product.sell.sell.controller;

import com.product.sell.sell.VO.ResultVO;
import com.product.sell.sell.enums.LoginEnum;
import com.product.sell.sell.enums.PayStatus;
import com.product.sell.sell.service.LoginService;
import com.product.sell.sell.util.EnumUtil;
import com.product.sell.sell.util.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("admin")
public class LoginController {

    @Autowired
    LoginService loginService;

    @GetMapping("login/index")
    public ModelAndView loginPage()
    {
        return new ModelAndView("order/login");
    }

    @GetMapping("login/verify")
    public ResultVO loginVerify(@RequestParam("username") String username,
                                @RequestParam("password") String password)
    {
        Integer result = loginService.verify(username, password);
        if (result == 0) {
            return ResultVOUtil.success();
        }

        return ResultVOUtil.error(result, EnumUtil.getByCode(result, LoginEnum.class).getMsg());
    }
}
