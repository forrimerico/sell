package com.product.sell.sell.repositories;

import com.product.sell.sell.dao.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerInfoRepository extends JpaRepository<SellerInfo, String> {
    SellerInfo findByOpenid(String openId);
}
