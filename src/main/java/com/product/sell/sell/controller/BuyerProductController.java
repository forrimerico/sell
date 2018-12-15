package com.product.sell.sell.controller;

import com.product.sell.sell.VO.ProductInfoVO;
import com.product.sell.sell.VO.ProductVO;
import com.product.sell.sell.VO.ResultVO;
import com.product.sell.sell.dao.ProductCategory;
import com.product.sell.sell.dao.ProductInfo;
import com.product.sell.sell.service.ProductCategoryService;
import com.product.sell.sell.service.ProductInfoService;
import com.product.sell.sell.util.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("/list")
    public ResultVO list()
    {
        ResultVO resultVO = new ResultVO();
//        ProductVO productVO = new ProductVO();
        // 查询所有上架商品
        List<ProductInfo> productInfos = productInfoService.findUp();

        // 查询类目
        // 先获取所有的type
        List<Integer> categoryType = new ArrayList<>();
        // 传统方法，for循环
//        for (ProductInfo productInfo : productInfos) {
//            categoryType.add(productInfo.getCategoryType());
//        }
        // lambda 表达式
        categoryType = productInfos.stream()
                .map(ProductInfo::getCategoryType)
                .collect(Collectors.toList());

        // 获取所有的类别
        List<ProductCategory> productCategories = productCategoryService.findByCategoryIn(categoryType);
        // 最终赋值给 ResultVO的data对象
        List<ProductVO> productVOs = new ArrayList<>();
        // 数据拼装
        for (ProductCategory productCategory : productCategories) {
            // 每一个类目
            ProductVO productVO = new ProductVO();
            // 设置name 和type
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());

            // 因为 foods也是一个list，所以要循环
            List<ProductInfoVO> productInfoList = new ArrayList<>();
            for (ProductInfo productInfo : productInfos) {
                // 如果food的type和 当前的类目一样，则放入 productInfoList。
                // 循环完成之后，整体放入 foods
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoList.add(productInfoVO);
                }
            }
            // 一个类目的所有食品
            productVO.setFoods(productInfoList);
            // 一个类目的拼装完成了~
            productVOs.add(productVO);
        }

        return ResultVOUtil.success(productVOs);
    }

}
