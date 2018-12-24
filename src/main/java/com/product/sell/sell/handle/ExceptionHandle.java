package com.product.sell.sell.handle;

import com.product.sell.sell.VO.ResultVO;
import com.product.sell.sell.exception.SellException;
import com.product.sell.sell.util.ResultVOUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
