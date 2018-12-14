package com.product.sell.sell.service.impl;

import com.product.sell.sell.dao.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryServiceImplTest {

    @Autowired
    private ProductCategoryServiceImpl productCategoryService;

    @Test
    public void findOne() throws Exception {
        ProductCategory productCategory = productCategoryService.findOne(1);
        Assert.assertEquals(new Integer(1), productCategory.getCategoryId());
    }

    @Test
    public void findAll() throws Exception {
        List<ProductCategory> productCategoryList = productCategoryService.findAll();
        Assert.assertNotNull(productCategoryList);
    }

    @Test
    public void findByCategoryIn() throws Exception {
        List<Integer> categories = Arrays.asList(1,2,3);
        List<ProductCategory> productCategoryList = productCategoryService.findByCategoryIn(categories);
        Assert.assertNotNull(productCategoryList);
    }

    @Test
    @Transactional
    public void save() throws Exception {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryType(1);
        productCategory.setCategoryName("test");
        ProductCategory result = productCategoryService.save(productCategory);
        Assert.assertNotNull(result);
    }
}