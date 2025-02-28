package com.example.service;

import com.example.dto.SelectAllKindName;
import com.example.dto.SelectTypeNames;
import com.example.entity.User;

import java.util.List;

/**
 * @author liwu
 * @version 1.0
 * @date 2024/11/21
 * @注释：共同功能接口类
 */
public interface CommonService {

    //用户登录
    User userLogin(String userName, String passWord);

    //修改用户名
    int updateUserName(String userName,String userNewName);

    //修改用户密码
    int updateUserPassword(String userName,String userOldPassword,String userNewPassword);

    //查看所有种类名
    List<SelectAllKindName> selectAllKindName();
    //查看所有分类名
    List<SelectTypeNames> selectAllType(String kindName);
    //拦截器实现
    boolean userInter(String userName);
    //查看种类总数
    int selectAllKindCount();
    //查看分类总数
    int selectAllTypeCount();
    //查看上线语料总数
    int selectAllOnlineCount();
    //查看种类下语料上线数
    int selectKindOnlineCorCount(String kindName);

}
