package com.product.sell.sell.service.impl;

import com.product.sell.sell.converter.OrderMasterToOrderDTO;
import com.product.sell.sell.dao.OrderDetail;
import com.product.sell.sell.dao.OrderMaster;
import com.product.sell.sell.dao.ProductInfo;
import com.product.sell.sell.dto.CartDTO;
import com.product.sell.sell.dto.OrderDTO;
import com.product.sell.sell.enums.OrderStatus;
import com.product.sell.sell.enums.PayStatus;
import com.product.sell.sell.enums.ResultEnum;
import com.product.sell.sell.exception.SellException;
import com.product.sell.sell.repositories.OrderDetailRepository;
import com.product.sell.sell.repositories.OrderMasterRepository;
import com.product.sell.sell.service.OrderService;
import com.product.sell.sell.service.ProductInfoService;
import com.product.sell.sell.util.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    ProductInfoService productInfoService;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    OrderMasterRepository orderMasterRepository;

    /**
     * 创建订单
     * @param orderDTO
     * @return
     */
    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {
        // 初始化一个订单总价
        BigDecimal orderAmount = new BigDecimal(0);
        // 生成一个唯一的订单主键
        String orderId = KeyUtil.generateKey();
        // 获取 cartdto list 的常规方案
        // cartdto list 是用来减库存的
//        List<CartDTO> cartDTOList = new ArrayList<>();
//        1、查询商品
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            // 根据订单查询商品信息
            ProductInfo productInfo = productInfoService.findOne(orderDetail.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            // 计算总价(订单总价)
            orderAmount = productInfo.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmount);
            // 订单详情入库
            // detailId  通过随机数来生成。
            // 属性拷贝。把 productInfo 的内容拷贝给 orderDetail。
            BeanUtils.copyProperties(productInfo, orderDetail);
            orderDetail.setDetailId(KeyUtil.generateKey());
            orderDetail.setOrderId(orderId);
            // 订单详情入库
            // 每个订单可能有多个订单详情
            orderDetailRepository.save(orderDetail);
            // 获取 cartdto list 的常规方案
//            CartDTO cartDTO = new CartDTO(orderDetail.getProductId(), orderDetail.getProductQuantity());
//            cartDTOList.add(cartDTO);
        }
//        3、写入数据库
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatus.NEW.getCode());
        orderMaster.setPayStatus(PayStatus.WAIT.getCode());

        orderMasterRepository.save(orderMaster);
//        4、扣库存
        // 获取 cartdto list 的 lambda 表达式方案
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream().map(e ->
                new CartDTO(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());

        productInfoService.decreaseStock(cartDTOList);

        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId)
    {
        OrderDTO result = new OrderDTO();
        OrderMaster orderMaster = orderMasterRepository.findOne(orderId);
        if (orderMaster == null) {
            throw new SellException(ResultEnum.ORDER_MASTER_ERR);
        }

        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderMaster.getOrderId());
        if (orderDetailList == null) {
            throw new SellException(ResultEnum.ORDER_DETAIL_ERR);
        }
        BeanUtils.copyProperties(orderMaster, result);
        result.setOrderDetailList(orderDetailList);

        return result;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenId, Pageable pageable)
    {
       Page<OrderMaster> orderMasterPage = orderMasterRepository.findByBuyerOpenid(buyerOpenId, pageable);
       List<OrderDTO> orderMasterList = OrderMasterToOrderDTO.convert(orderMasterPage.getContent());

       return new PageImpl<OrderDTO>(orderMasterList, pageable, orderMasterPage.getTotalElements());
    }

    @Override
    public OrderDTO cancle(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO paid(OrderDTO orderDTO) {
        return null;
    }
}
