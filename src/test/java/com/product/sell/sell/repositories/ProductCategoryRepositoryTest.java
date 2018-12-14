package com.product.sell.sell.repositories;

import com.product.sell.sell.dao.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {
    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Test
    public void findOne()
    {
        ProductCategory obj = productCategoryRepository.findOne(1);
        System.out.println(obj.toString());
    }

    @Test
    @Transactional
    public void  save()
    {
        ProductCategory obj = new ProductCategory();
        obj.setCategoryName("femaleLoved");
        obj.setCategoryType(2);
        ProductCategory result = productCategoryRepository.save(obj);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByCategoryTypeInTest()
    {
        List<Integer> typeList = Arrays.asList(2,3,4);
        List<ProductCategory> result = productCategoryRepository.findByCategoryTypeIn(typeList);
        Assert.assertNotEquals(0, result.size());
    }
}