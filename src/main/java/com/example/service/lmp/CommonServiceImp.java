package com.example.service.lmp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.entity.User;
import com.example.mapper.UserMapper;
import com.example.service.CommonService;
import com.example.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Objects;

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

    //用户登录
    @Override
    public User userLogin(String userName, String passWord) {
        MD5Utils md5Utils = new MD5Utils(); //md5加密算法
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        String dbPassWord = md5Utils.md5(passWord); //使用md5加密算法对前端传递的密码加密
        queryWrapper.eq("user_name",userName).eq("password",dbPassWord); //包装实例
        User user = userMapper.selectOne(queryWrapper); //查询库中是否有该用户
        return user;
    }

    //修改用户名
    @Override
    public int updateUserName(String userName,String userNewName) {
        QueryWrapper<User> queryWrapper =new QueryWrapper<>();
        queryWrapper.eq("user_name",userNewName);
        User user =userMapper.selectOne(queryWrapper);  //查询更改的用户名在数据库中是否存在
        if (user == null){  //不存在执行修改语句
            return userMapper.updateUsername(userName,userNewName);
        }else {
            return 0;
        }
    }

    //修改密码
    @Override
    public int updateUserPassword(String userName, String userNewPassword) {
        MD5Utils md5Utils =new MD5Utils();
        String md5Password = md5Utils.md5(userNewPassword); //将用户旧密码加密
        String dbPassword = userMapper.selectUserPassword(userName); //查找用户在数据库中密码
        if (!Objects.equals(md5Password, dbPassword)){
            return userMapper.updateUserPassword(userName,md5Password);
        }
        else {
            return 0;
        }
    }

    //拦截器
    @Override
    public boolean userInter(String userName) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name",userName); //查找数据库中是否有该数据
        User user =userMapper.selectOne(queryWrapper);
        return user!=null;
    }

}
