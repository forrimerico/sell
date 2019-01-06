package com.product.sell.sell.service;

import com.product.sell.sell.dao.SellerInfo;

public interface SellerService {
    SellerInfo findSellerByOpenId(String openId);
}
