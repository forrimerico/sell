package com.product.sell.sell.util;

public class MathUtil {
    private static final Double MoneyRange = 0.01;

    public static Boolean equals(Double d1, Double d2)
    {
        Double result = Math.abs(d1 - d2);

        return result < 0.01;
    }
}
