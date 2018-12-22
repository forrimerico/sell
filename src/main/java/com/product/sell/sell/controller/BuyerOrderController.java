package com.product.sell.sell.controller;

        import com.product.sell.sell.VO.ResultVO;
        import com.product.sell.sell.converter.OrderFormToOrderDTO;
        import com.product.sell.sell.dto.OrderDTO;
        import com.product.sell.sell.enums.ResultEnum;
        import com.product.sell.sell.exception.SellException;
        import com.product.sell.sell.form.OrderForm;
        import com.product.sell.sell.service.BuyerService;
        import com.product.sell.sell.service.OrderService;
        import com.product.sell.sell.util.ResultVOUtil;
        import lombok.extern.slf4j.Slf4j;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.data.domain.Page;
        import org.springframework.data.domain.PageRequest;
        import org.springframework.util.CollectionUtils;
        import org.springframework.util.StringUtils;
        import org.springframework.validation.BindingResult;
        import org.springframework.web.bind.annotation.*;

        import javax.validation.Valid;
        import java.util.HashMap;
        import java.util.List;
        import java.util.Map;

@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    BuyerService buyerService;
    // 创建订单
    @PostMapping("/create")
    public ResultVO<Map<String, String>> create(@Valid OrderForm orderForm,
                                                BindingResult bindingResult)
    {
        if (bindingResult.hasErrors()) {
            log.error("【创建订单】参数不正确：orderForm={}", orderForm);
            throw new SellException(ResultEnum.PARAM_ERR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDTO orderDTO = OrderFormToOrderDTO.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【创建订单】购物车为空：orderForm={}", orderForm);
            throw new SellException(ResultEnum.CART_EMPTY);
        }
        OrderDTO result = orderService.create(orderDTO);

        Map<String, String> map = new HashMap<>();
        map.put("order_id", result.getOrderId());

        return ResultVOUtil.success(map);
    }

    // 订单列表
    @GetMapping("/list")
    public ResultVO<List<OrderDTO>> list(@RequestParam("openid") String openid,
                                         @RequestParam(value = "page", defaultValue = "0") Integer page,
                                         @RequestParam(value = "size", defaultValue = "10") Integer size)
    {
        if (StringUtils.isEmpty(openid)) {
            log.error("【查询订单列表】OPENID为空！");
            throw new SellException(ResultEnum.PARAM_ERR);
        }
        PageRequest pageRequest  = new PageRequest(page, size);
        Page<OrderDTO> result = orderService.findList(openid, pageRequest);

        return ResultVOUtil.success(result);
    }

    // 订单详情
    @GetMapping("detail")
    public ResultVO<OrderDTO> detail(@RequestParam("openid") String openid,
                                     @RequestParam("orderid") String orderid)
    {
        // 用orderService是不安全的做法 需要判断下 订单的openid与传来的openid是否一致
        OrderDTO orderDTO = buyerService.findOrderOne(openid, orderid);

        return ResultVOUtil.success(orderDTO);
    }

    // 取消订单
    @PostMapping("cancle")
    public ResultVO cancle(@RequestParam("openid") String openid,
                           @RequestParam("orderid") String orderid)
    {
        OrderDTO orderDTO = buyerService.cancleOrder(openid, orderid);
        orderService.cancle(orderDTO);

        return ResultVOUtil.success();
    }
}
