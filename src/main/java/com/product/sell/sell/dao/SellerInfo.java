package com.product.sell.sell.dao;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="seller_info")
@Entity
@Data
public class SellerInfo {

    @Id
    private String id;

    private String username;

    private String password;

    private String openid;
}
