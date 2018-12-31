package com.product.sell.sell.service.impl;

import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundRequest;
import com.lly835.bestpay.model.RefundResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import com.product.sell.sell.dto.OrderDTO;
import com.product.sell.sell.enums.ResultEnum;
import com.product.sell.sell.exception.SellException;
import com.product.sell.sell.service.OrderService;
import com.product.sell.sell.service.PayService;
import com.product.sell.sell.util.JsonUtil;
import com.product.sell.sell.util.MathUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
public class PayServiceImpl implements PayService {
    @Autowired
    private BestPayServiceImpl bestPayService;

    @Autowired
    private OrderService orderService;

    private final static String ORDER_NAME = "微信点餐";

    @Override
    public PayResponse create(OrderDTO orderDTO) {
        PayRequest payRequest = new PayRequest();
        payRequest.setOpenid(orderDTO.getBuyerOpenid());
        payRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        payRequest.setOrderId(orderDTO.getOrderId());
        payRequest.setOrderName(ORDER_NAME);
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        log.info("支付参数：{}", JsonUtil.toJson(payRequest));
        PayResponse payResponse = bestPayService.pay(payRequest);
        log.info("支付结果:{}", JsonUtil.toJson(payResponse));

        return payResponse;
    }

    @Override
    public PayResponse notify(String nofityData) {
        // 1. 验证签名 bestPay处理
        // 2. 支付的状态是否是成功 besetPay处理
        // 3. 支付的金额校验
        // 4. 支付人是否与下单人一致。这个根据不同的业务需求来定。

        PayResponse payResponse = bestPayService.asyncNotify(nofityData);
        log.info("【微信支付】异步通知， payResponse={}", payResponse);

        OrderDTO orderDTO = orderService.findOne(payResponse.getOrderId());

        if (orderDTO == null) {
            log.error("【微信支付】订单不存在！orderDTO={}", orderDTO);
            throw new SellException(ResultEnum.ORDER_MASTER_ERR);
        }
        // 这里一个是bigdecimal，一个是double。
        // 不能用equals 方法。
        // 还要保证精度一致 0.1 和 0.10
        // 可以根据两个差值是否小于 0.01 来判断是否相等
//        if (orderDTO.getOrderAmount().compareTo(new BigDecimal(payResponse.getOrderAmount())) != 0) {
        if (!MathUtil.equals(payResponse.getOrderAmount(), orderDTO.getOrderAmount().doubleValue())) {
            log.error("【微信支付】订单金额不一致！订单号={}, 订单金额={}， 微信通知金额={}",
                    orderDTO.getOrderId(),
                    orderDTO.getOrderAmount(),
                    payResponse.getOrderAmount());

            throw new SellException(ResultEnum.WXPAY_NOTIFY_MONEY_VERIFY_ERR);
        }
        // 修改订单的支付状态  入参是orderDTO
        orderService.paid(orderDTO);

        return payResponse;
    }

    @Override
    public RefundResponse refund(OrderDTO orderDTO) {
        RefundRequest refundRequest = new RefundRequest();
        refundRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        refundRequest.setOrderId(orderDTO.getOrderId());
        refundRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        log.info("【微信退款】request={}", JsonUtil.toJson(refundRequest));
        RefundResponse refundResponse = bestPayService.refund(refundRequest);
        log.info("【微信退款】response={}", JsonUtil.toJson(refundResponse));

        return refundResponse;
    }
}
