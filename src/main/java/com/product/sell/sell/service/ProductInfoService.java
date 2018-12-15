package com.product.sell.sell.service;

import com.product.sell.sell.dao.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductInfoService {

    ProductInfo findOne(String productId);

    Page<ProductInfo> findAll(Pageable pageable);

    // 查询上架商品
    List<ProductInfo> findUp();

    ProductInfo save(ProductInfo productInfo);

}
