package com.product.sell.sell.service.impl;

import com.product.sell.sell.dao.ProductInfo;
import com.product.sell.sell.dto.CartDTO;
import com.product.sell.sell.enums.ProductStatus;
import com.product.sell.sell.enums.ResultEnum;
import com.product.sell.sell.exception.SellException;
import com.product.sell.sell.repositories.ProductInfoRepository;
import com.product.sell.sell.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    ProductInfoRepository productInfoRepository;

    @Override
    public ProductInfo findOne(String productId) {
        return productInfoRepository.findOne(productId);
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return productInfoRepository.findAll(pageable);
    }

    @Override
    public List<ProductInfo> findUp() {
        return productInfoRepository.findByProductStatus(ProductStatus.UP.getCode());
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return productInfoRepository.save(productInfo);
    }

    @Override
    @Transactional
    public void increaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            ProductInfo productInfo = productInfoRepository.findOne(cartDTO.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer result = productInfo.getProductStock() + cartDTO.getProductQuantity();
            productInfo.setProductStock(result);
            productInfoRepository.save(productInfo);
        }
    }

    /**
     * 扣库存
     * 需要解决一下 可能出现的超卖情况的发生
     * @param cartDTOList
     */
    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            ProductInfo productInfo = productInfoRepository.findOne(cartDTO.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            // 扣库存之后的结果
            int leftProductNum = productInfo.getProductStock() - cartDTO.getProductQuantity();
            if (leftProductNum < 0) {
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERR);
            }
            productInfo.setProductStock(leftProductNum);
            productInfoRepository.save(productInfo);
        }
    }
}
