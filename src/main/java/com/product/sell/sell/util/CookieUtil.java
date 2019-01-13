package com.product.sell.sell.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class CookieUtil {

    public static Cookie setCookie(String cookieName, String cookieValue, int maxAge)
    {
        Cookie cookie = new Cookie(cookieName, cookieValue);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);

        return cookie;
    }

    public static Cookie getCookie(HttpServletRequest httpServletRequest,
                                   String name)
    {
        Map<String, Cookie> cookieMap = readCookieMap(httpServletRequest);
        if (cookieMap.containsKey(name)) {
            return cookieMap.get(name);
        }

        return null;
    }

    private static Map<String, Cookie> readCookieMap(HttpServletRequest httpServletRequest)
    {
        Cookie[] cookies = httpServletRequest.getCookies();
        Map<String, Cookie> cookieMap = new HashMap<>();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(), cookie);
            }
        }

        return cookieMap;
    }

}
