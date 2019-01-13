package com.product.sell.sell.handle;

import com.product.sell.sell.VO.ResultVO;
import com.product.sell.sell.exception.MyLoginException;
import com.product.sell.sell.exception.SellException;
import com.product.sell.sell.util.ResultVOUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler(value = SellException.class)
    @ResponseBody
    public ResultVO handle(Exception e)
    {
        if (e instanceof SellException) {
            SellException sellException = (SellException) e;
            return ResultVOUtil.error(sellException.getCode(), sellException.getMessage());
        }

        return ResultVOUtil.error(-1, e.getMessage());
    }

    // 拦截登陆异常
    @ExceptionHandler(value = MyLoginException.class)
    public ModelAndView handleAuthorizeException()
    {
        return new ModelAndView("redirect:http://127.0.0.1:8080/sell/admin/login/index");
    }
}
