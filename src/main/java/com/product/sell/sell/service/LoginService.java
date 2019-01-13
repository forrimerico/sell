package com.product.sell.sell.service;

import javax.servlet.http.HttpServletResponse;

public interface LoginService {
    public Integer verify(String username, String password, HttpServletResponse response);
}
