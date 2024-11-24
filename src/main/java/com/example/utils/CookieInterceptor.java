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
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("cookieName".equals(cookie.getName())) {
                    // 在这里可以进行更复杂的验证逻辑，比如根据用户ID查询数据库验证用户是否存在等
                    String userName = cookie.getValue();
                    // 假设你有一个UserService来验证用户ID是否有效
                    if (commonService.userInter(userName)) {
                        return true;
                    }
                }
            }
        }
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

