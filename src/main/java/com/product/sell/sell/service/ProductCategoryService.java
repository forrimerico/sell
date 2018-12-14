package com.product.sell.sell.service;

import com.product.sell.sell.dao.ProductCategory;
import org.jvnet.hk2.annotations.Service;

import java.util.List;


public interface ProductCategoryService {
    // 查找一个类目
    ProductCategory findOne(Integer categoryId);

    // 查找所有类目
    List<ProductCategory> findAll();

    // 根据类目ID查找
    List<ProductCategory> findByCategoryIn(List<Integer> categories);

    // 保存
    ProductCategory save(ProductCategory productCategory);
}
