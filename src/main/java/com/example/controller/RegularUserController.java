package com.example.controller;

import com.example.dto.RegularUserEnroll;
import com.example.entity.User;
import com.example.service.RegularUserService;
import com.example.utils.api.BaseApiService;
import com.example.utils.api.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author liwu
 * @version 1.0
 * @date 2024/11/21
 * @注释：普通用户控制类
 */
@RestController
@CrossOrigin
@ResponseBody
@RequestMapping("/regularuser")
public class RegularUserController extends BaseApiService {
    @Autowired
    private RegularUserService regularUserService;

    //普通用户注册
    @PostMapping("/enroll")
    public BaseResponse regularuserEnroll(@RequestBody RegularUserEnroll regularUserEnroll){
        String userName = regularUserEnroll.getUserName(); //获取注册时前端传递的用户名和密码
        String passWord = regularUserEnroll.getPassWord();
        int a = regularUserService.regularuserEnroll(userName,passWord); //调用注册方法
        if (a!=0){
            return setResultDb(a); //不为零，注册成功，返回状态码为200
        }else {
            return setResultError("该用户已存在，请重新注册");
        }
    }
}
