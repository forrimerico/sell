package com.product.sell.sell.controller;

import com.product.sell.sell.dto.OrderDTO;
import com.product.sell.sell.enums.ResultEnum;
import com.product.sell.sell.exception.SellException;
import com.product.sell.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@Slf4j
@RequestMapping("seller/order")
public class SellOrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size,
                             Map<String, Object> map)
    {
        PageRequest request = new PageRequest(page - 1, size);
        Page<OrderDTO> orderDTOPage = orderService.findList(request);

        map.put("orderDTOPage", orderDTOPage);
        map.put("currentPage", page);
        return new ModelAndView("order/list", map);
    }

    @GetMapping("cancle")
    public ModelAndView cancle(@RequestParam("orderId") String orderId,
                               Map<String, Object> map)
    {
        try {
            OrderDTO orderDTO = orderService.findOne(orderId);
            orderService.cancle(orderDTO);
        } catch (SellException e) {
            log.error("【取消订单】不存在！");
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/order/list");
            return new ModelAndView("common/error", map);
        }
        map.put("msg", ResultEnum.SUCCESS.getMsg());
        map.put("url", "/sell/seller/order/list");
        return new ModelAndView("common/success", map);
    }

    @GetMapping("detail")
    public ModelAndView detail(@RequestParam("orderId") String orderId,
                               Map<String, Object> map)
    {
        OrderDTO orderDTO = new OrderDTO();
        try {
            orderDTO = orderService.findOne(orderId);
        } catch (SellException e) {
            log.error("【查询订单详情错误！exception：{}】", e);
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/order/list");
            return new ModelAndView("common/error", map);
        }
        map.put("orderDTO", orderDTO);
        return new ModelAndView("order/detail", map);
    }

    @GetMapping("finish")
    public ModelAndView finish(@RequestParam("orderId") String orderId,
                               Map<String, Object> map)
    {
        try {
            OrderDTO orderDTO = orderService.findOne(orderId);
            orderService.finish(orderDTO);
        } catch (SellException e) {
            log.error("【取消订单】不存在！");
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/order/list");
            return new ModelAndView("common/error", map);
        }
        map.put("msg", ResultEnum.SUCCESS.getMsg());
        map.put("url", "/sell/seller/order/list");
        return new ModelAndView("common/success", map);
    }
}
