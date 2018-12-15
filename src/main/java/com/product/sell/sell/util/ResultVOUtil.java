package com.product.sell.sell.util;

import com.product.sell.sell.VO.ResultVO;

public class ResultVOUtil {
    public static ResultVO success(Object object)
    {
        ResultVO resultVO = new ResultVO();
        resultVO.setData(object);
        resultVO.setMessage("success");
        resultVO.setCode(0);

        return resultVO;
    }

    public static ResultVO success()
    {
        return success(null);
    }

    public static ResultVO error(Integer code, String msg)
    {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMessage(msg);
        resultVO.setData(null);

        return resultVO;
    }
}
