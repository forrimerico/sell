package com.product.sell.sell.util;

import com.product.sell.sell.enums.CodeEnum;

public class EnumUtil {
    // T 这里有可能是任何一种类型的枚举类
    // T extends codeenum 这个接口 保证了他一定实现了接口里的方法
    // 这就是写 CodeEnum，并让你的枚举类实现它的原因所在
    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass)
    {
        for (T each : enumClass.getEnumConstants()) {
            if (code.equals(each.getCode())) {
                return each;
            }
        }

        return null;
    }
}
