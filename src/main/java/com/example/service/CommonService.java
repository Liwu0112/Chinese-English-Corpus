package com.example.service;

import com.example.entity.User;

/**
 * @author liwu
 * @version 1.0
 * @date 2024/11/21
 * @注释：共同功能接口类
 */
public interface CommonService {

    //用户登录
    User userLogin(String userName, String passWord);

    //拦截器实现
    boolean userInter(String userName);
}
