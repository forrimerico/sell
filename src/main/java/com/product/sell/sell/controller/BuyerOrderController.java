package com.product.sell.sell.controller;

        import com.product.sell.sell.VO.ResultVO;
        import com.product.sell.sell.converter.OrderFormToOrderDTO;
        import com.product.sell.sell.dto.OrderDTO;
        import com.product.sell.sell.enums.ResultEnum;
        import com.product.sell.sell.exception.SellException;
        import com.product.sell.sell.form.OrderForm;
        import com.product.sell.sell.service.OrderService;
        import com.product.sell.sell.util.ResultVOUtil;
        import lombok.extern.slf4j.Slf4j;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.util.CollectionUtils;
        import org.springframework.validation.BindingResult;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RestController;

        import javax.validation.Valid;
        import java.util.HashMap;
        import java.util.Map;

@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {
    @Autowired
    OrderService orderService;
    // 创建订单
    @RequestMapping("/create")
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

    // 订单详情

    // 取消订单
}
