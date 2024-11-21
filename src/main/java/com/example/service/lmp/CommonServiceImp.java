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
 * @注释：
 */
@Service
public class CommonServiceImp implements CommonService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User userLogin(String userName, String passWord) {
        MD5Utils md5Utils = new MD5Utils();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        String dbPassWord = md5Utils.md5(passWord);
        queryWrapper.eq("username",userName).eq("passWord",dbPassWord);
        User user = userMapper.selectOne(queryWrapper);
        return user;
    }
}
