package com.example.controller;

import com.example.dto.*;
import com.example.entity.User;
import com.example.service.CommonService;
import com.example.utils.CookieInterceptor;
import com.example.utils.api.BaseApiService;
import com.example.utils.api.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

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
            Cookie cookie = new Cookie("cookieName",String.valueOf(user.getUserName())); //生成一个包含user_id的Cookie
            cookie.setPath("/");  //可全局访问该Cookie
            cookie.setMaxAge(3600); //有效时间一小时
            response.addCookie(cookie);
           return setResultSuccessData(user); //返回状态码为200，返回值为库中该用户数据
        }else {
            return setResultError(); //为空，返回状态码为500
        }
    }

    //查看所有种类名
    @GetMapping("/selectallkind")
    public BaseResponse adminSelectAllKind() {
        List<SelectAllKindName> list = commonService.selectAllKindName();
        return setResultSuccessData(list);
    }

    //用种类名查看其所有分类名
    @GetMapping("/selectalltype")
    public BaseResponse adminSelectAllType(@RequestParam("kindName") String kindName) {
        List<SelectTypeNames> result = commonService.selectAllType(kindName);
        return setResultSuccessData(result);
    }
    //修改用户名
    @PostMapping("/updateusername")
    public BaseResponse userUpdateName(@RequestBody UpdateUserNameDto updateUserNameDto,HttpServletResponse response) {
        String userName = updateUserNameDto.getUserName();
        String userNewName = updateUserNameDto.getUserNewName();
        int a = commonService.updateUserName(userName,userNewName);
        if (a!=0){
            Cookie cookie = new Cookie("cookieName",String.valueOf(userNewName));
            cookie.setPath("/");  //可全局访问该Cookie
            cookie.setMaxAge(3600); //有效时间一小时
            response.addCookie(cookie);
            return setResultSuccess();
        }else {
            return setResultError();
        }
    }
    //修改用户密码
    @PostMapping("/updatepassword")
    public BaseResponse userUpdatePassword(@RequestBody UpdateUserPasswordDto updateUserPasswordDto,HttpServletResponse response){
        String userName = updateUserPasswordDto.getUserName();
        String userOldPassword = updateUserPasswordDto.getUserOldPassword();
        String userNewPassword = updateUserPasswordDto.getUserNewPassword();
        int a = commonService.updateUserPassword(userName,userOldPassword,userNewPassword);
        if (a!=0){
            cookieInterceptor.deleteCookie(response,"cookieName");
            return setResultSuccess();
        }else {
            return setResultError();
        }
    }
    //用户注销登录
    @PostMapping("/logout")
    public BaseResponse usersLogout(HttpServletResponse response) {
        // 调用拦截器中的方法删除Cookie
        cookieInterceptor.deleteCookie(response,"cookieName");
        return setResultSuccess();
    }
    //查看种类总数
    @GetMapping("/selectallkindcount")
    public BaseResponse adminSelectAllKinds() {
        int result = commonService.selectAllKindCount();
        return setResultSuccessData(result);
    }

    //查看分类总数
    @GetMapping("/selectalltypecount")
    public BaseResponse adminSelectAllTypes() {
        int result = commonService.selectAllTypeCount();
        return setResultSuccessData(result);
    }
        //查看上线总数
    @GetMapping("/selectallonlinecount")
    public BaseResponse adminSelectAllOnlinecount(){
        int result = commonService.selectAllOnlineCount();
        return setResultSuccessData(result);
    }

    //按种类名查询语料上线数
    @GetMapping("/selectonlinebykindname")
    public BaseResponse amdinSelectOnlineByKindName(@RequestParam("kindName") String kindName) {
        int result = commonService.selectKindOnlineCorCount(kindName);
        return setResultSuccessData(result);
    }
}
