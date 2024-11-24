package com.example.controller;

import com.example.dto.LoginDto;
import com.example.entity.User;
import com.example.service.CommonService;
import com.example.utils.CookieInterceptor;
import com.example.utils.api.BaseApiService;
import com.example.utils.api.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
/**
 * @author liwu
 * @version 1.0
 * @date 2024/11/21
 * @注释：
 * 相同模块控制类
 * 功能1 用户登录功能
 * 功能2 用户注销登录功能
 */
@RestController
@CrossOrigin
@ResponseBody
@RequestMapping("/common")
public class CommonController extends BaseApiService {
    @Autowired
    private CommonService commonService;
    @Autowired
    private CookieInterceptor cookieInterceptor;

    //用户登录功能
    @PostMapping("/login")
    public BaseResponse usersLogin(@RequestBody LoginDto loginDto, HttpServletResponse response){
        String userName = loginDto.getUserName();  //获取前端传递用户名
        String passWord = loginDto.getPassWord();  //获取前端传递密码
        User user = commonService.userLogin(userName,passWord); //调用登录方法
        if (user!=null){
            Cookie cookie = new Cookie("cookieName",String.valueOf(user.getUsername())); //生成一个包含user_id的Cookie
            cookie.setPath("/");  //可全局访问该Cookie
            cookie.setMaxAge(3600); //有效时间一小时
            response.addCookie(cookie);
           return setResultSuccessData(user); //返回状态码为200，返回值为库中该用户数据
        }else {
            return setResultError(); //为空，返回状态码为500
        }
    }

    //用户注销登录
    @GetMapping("/logout")
    public BaseResponse usersLogout(HttpServletResponse response) {
        // 调用拦截器中的方法删除Cookie
        cookieInterceptor.deleteCookie(response,"cookieName");
        return setResultSuccess();
    }
}
