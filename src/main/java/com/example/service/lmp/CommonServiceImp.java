package com.example.service.lmp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.entity.User;
import com.example.mapper.UserMapper;
import com.example.service.CommonService;
import com.example.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liwu
 * @version 1.0
 * @date 2024/11/21
 * @注释：共同模块功能实现类
 */
@Service
public class CommonServiceImp implements CommonService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User userLogin(String userName, String passWord) {
        MD5Utils md5Utils = new MD5Utils(); //md5加密算法
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        String dbPassWord = md5Utils.md5(passWord); //使用md5加密算法对前端传递的密码加密
        queryWrapper.eq("username",userName).eq("password",dbPassWord); //包装实例
        User user = userMapper.selectOne(queryWrapper); //查询库中是否有该用户
        return user;
    }
}
