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
 * 该模块为普通用户和管理员的共同功能模块
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
        String userName = loginDto.getUserName();
        String passWord = loginDto.getPassWord();
        User user = commonService.userLogin(userName,passWord);
        if (user!=null){
            Object role = user.getRole();
            return setResult(200,"登录成功！",role);
        }else {
            return setResultError();
        }
    }
}
