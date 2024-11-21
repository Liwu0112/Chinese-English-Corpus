package com.example.controller;

import com.example.dto.LoginDto;
import com.example.entity.User;
import com.example.service.CommonService;
import com.example.utils.api.BaseApiService;
import com.example.utils.api.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    //用户登录功能
    @PostMapping("/login")
    public BaseResponse userLogin(@RequestBody LoginDto loginDto){
        String userName = loginDto.getUserName();  //获取前端传递用户名
        String passWord = loginDto.getPassWord();  //获取前端传递密码
        User user = commonService.userLogin(userName,passWord); //调用登录方法
        if (user!=null){
            Object role = user.getRole(); //获取用户类型
            return setResult(200,"登录成功！",role);
        }else {
            return setResultError(); //为空，返回状态码为500
        }
    }
}
