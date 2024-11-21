package com.example.service;

import com.example.entity.User;

/**
 * @author liwu
 * @version 1.0
 * @date 2024/11/21
 * @注释：
 */
public interface CommonService {

    //用户登录
    User userLogin(String userName,String passWord);
}
