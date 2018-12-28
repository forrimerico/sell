package com.product.sell.sell.controller;

import com.lly835.bestpay.model.PayResponse;
import com.product.sell.sell.dto.OrderDTO;
import com.product.sell.sell.enums.ResultEnum;
import com.product.sell.sell.exception.SellException;
import com.product.sell.sell.service.OrderService;
import com.product.sell.sell.service.PayService;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("pay")
public class PayController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private PayService payService;

    @GetMapping("create")
    public ModelAndView create(@RequestParam("orderId") String orderId,
                               @RequestParam("returnUrl") String returnUrl,
                               Map<String, Object> map)
    {
        OrderDTO orderDTO = orderService.findOne(orderId);
        if (orderDTO == null) {
            throw new SellException(ResultEnum.ORDER_MASTER_ERR);
        }

        // 发起支付
        PayResponse payResponse = payService.create(orderDTO);
        map.put("payResponse", payResponse);
        map.put("returnUrl", returnUrl);
        return new ModelAndView("pay/create", map);
    }
}
