package com.product.sell.sell.controller;

import com.product.sell.sell.service.SellKillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SellKillController {
    @Autowired
    SellKillService sellKillService;

    @GetMapping("kill")
    public String sellKillController(@RequestParam("productId") String productId)
    {
        return sellKillService.sellKillProduct(productId);
    }
}
