package com.product.sell.sell.dao;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="product_category")
@Entity
@Data
public class ProductCategory {
    @Id
    @GeneratedValue
    private Integer categoryId;

    private String categoryName;

    private Integer categoryType;

    @Override
    public String toString()
    {
        return "ProductCategoryID:" + this.getCategoryId() + "\n" +
                "ProductCategoryName:" + this.getCategoryName() + "\n" +
                "ProductCategoryType:" + this.getCategoryType() + "\n";
    }
}
