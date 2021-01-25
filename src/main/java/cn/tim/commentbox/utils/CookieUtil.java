package cn.tim.commentbox.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 有关cookie的工具类
 */
public class CookieUtil {
    /**
     * 设置cookie
     */
    public static void set(HttpServletResponse response,
                           String name,
                           String value,
                           int maxAge) {
        //开发环境
        Cookie cookieDev = new Cookie(name, value);
        cookieDev.setPath("/");
        cookieDev.setMaxAge(maxAge);
        response.addCookie(cookieDev);
    }

    /**
     * 获取cookie
     */
    public static Cookie get(HttpServletRequest request,
                             String name) {
        Map<String, Cookie> cookieMap = readCookieMap(request);
        return cookieMap.getOrDefault(name, null);
    }

    /**
     * 将cookie封装成Map
     */
    private static Map<String, Cookie> readCookieMap(HttpServletRequest request) {
        Map<String, Cookie> cookieMap = new HashMap<>();
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie: cookies) {
                cookieMap.put(cookie.getName(), cookie);
            }
        }
        return cookieMap;
    }
}
