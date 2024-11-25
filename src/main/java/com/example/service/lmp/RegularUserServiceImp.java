package com.example.service.lmp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.dto.TransTextDto;
import com.example.entity.User;
import com.example.mapper.CorpusMapper;
import com.example.mapper.UserMapper;
import com.example.service.RegularUserService;
import com.example.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liwu
 * @version 1.0
 * @date 2024/11/21
 * @注释：普通用户模块实现类
 */
@Service
public class RegularUserServiceImp implements RegularUserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CorpusMapper corpusMapper;

    //普通用户注册
    @Override
    public int regularuserEnroll(String userName, String passWord) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", userName);
        User user = userMapper.selectOne(queryWrapper); //查找数据库中是否存在该用户名信息
        if (user == null) {   //为空，继续操作
            MD5Utils md5Utils = new MD5Utils();
            String dbPassWord = md5Utils.md5(passWord);  //MD5加密
            User user1 = new User();
            user1.setUserName(userName);  //注册户的用户名
            user1.setPassword(dbPassWord); //注册用户的密码
            user1.setRole("regular_user"); //注册用户为普通用户，所以角色值为regular_user
            return userMapper.insert(user1);
        } else {
            return 0; //不为空，返回0
        }
    }

    //使用中文语料查询英文语料
    @Override
    public List<TransTextDto> chToEn(String text) {
        String likeText = "%" + text + "%";  //模糊查询需要拼接
        return corpusMapper.selectEnglishTextChToEnDtos(likeText);  //返回对应数据
    }

    //使用英文语料查询中文语料
    @Override
    public List<TransTextDto> enToCh(String text) {
        String likeText = "%" + text + "%";  //模糊查询需要拼接
        return corpusMapper.selectChineseTextEnToChDtos(likeText);  //返回对应数据
    }


}
