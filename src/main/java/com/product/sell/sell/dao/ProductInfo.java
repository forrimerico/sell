package com.product.sell.sell.dao;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Table(name="product_info")
@Entity
@Data
public class ProductInfo {
    @Id
    private String productId;

    private String productName;

    private BigDecimal productPrice;

    private Integer productStock;

    private String productDescription;

    private String productIcon;

    private Integer productStatus;

    private Integer categoryType;

    public String toString()
    {
        return "productId:" + this.getProductId() + "\n" +
                "productName:" + this.getProductName() + "\n" +
                "productPrice:" + this.getProductPrice() + "\n" +
                "productStock:" + this.getProductPrice() + "\n" +
                "productDescription:" + this.getProductDescription() + "\n" +
                "productIcon:" + this.getProductIcon() + "\n" +
                "productStatus:" + this.getProductStatus() + "\n" +
                "categoryType:" + this.getCategoryType() + "\n";
    }
}
