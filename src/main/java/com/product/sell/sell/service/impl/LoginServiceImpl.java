package com.product.sell.sell.service.impl;

import com.product.sell.sell.service.LoginService;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Override
    public Integer verify(String username, String password) {
        return 1;
    }
}
