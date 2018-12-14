package com.product.sell.sell.repositories;

import com.product.sell.sell.dao.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {

    public List<ProductCategory> findByCategoryTypeIn(List<Integer> typeList);
}
