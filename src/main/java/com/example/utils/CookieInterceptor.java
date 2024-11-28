package com.example.utils;

import com.example.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author liwu
 * @version 1.0
 * @date 2024/11/22
 * @注释：该类为拦截器，主要作用为通过检验Cookie对用户进行鉴权以及删除cookie方法
 */
@Component
public class CookieInterceptor  implements HandlerInterceptor {
    @Autowired
    private CommonService commonService;


    //判断cookie是否有效
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 对 OPTIONS 请求放行
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK); // 返回 200 OK
            return true; // 放行
        }

        // 检查请求中的 Cookie
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("cookieName".equals(cookie.getName())) {
                    // 验证 Cookie 值
                    String userName = cookie.getValue();
                    if (commonService.userInter(userName)) {
                        return true; // 验证成功，放行
                    }
                }
            }
        }
        // 如果验证失败，返回 401 Unauthorized
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "未授权访问");
        return false;
    }


    //删除cookie方法
    public void deleteCookie(HttpServletResponse response, String cookieName) {
        Cookie cookie = new Cookie(cookieName, "");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}

