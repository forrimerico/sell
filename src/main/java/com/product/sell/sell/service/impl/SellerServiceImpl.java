package com.product.sell.sell.service.impl;

import com.product.sell.sell.dao.SellerInfo;
import com.product.sell.sell.repositories.SellerInfoRepository;
import com.product.sell.sell.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImpl implements SellerService {
    @Autowired
    SellerInfoRepository sellerInfoRepository;

    @Override
    public SellerInfo findSellerByOpenId(String openId) {
        return sellerInfoRepository.findByOpenid(openId);
    }
}
