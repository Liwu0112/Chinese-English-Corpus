package com.example.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.dto.ReUserInfo;
import com.example.entity.User;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
* @author Xiao-Li
* @description 针对表【t_user】的数据库操作Mapper
* @createDate 2024-11-21 22:14:35
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

    //修改用户名
    @Update("update t_user set user_name=#{userNewName} where user_name = #{userName}")
    int updateUsername(@Param("userName") String userName,@Param("userNewName") String userNewName);

    //查询用户名对应的密码
    @Select("select password from t_user where user_name=#{userName}")
    String selectUserPassword(@Param("userName")String userName);

    //修改用户密码
    @Update("update t_user set password=#{userNewPassword} where user_name=#{userName}")
    int updateUserPassword(@Param("userName")String userName,@Param("userNewPassword")String userNewPassword);

    //查询普通用户总数
    @Select("select count(1) from t_user where role='regular_user'")
    int selectAllReUser();
    //查看所有普通用户信息（编号，账户，注册时间，类型）
    @Select("select * from t_user where role='regular_user'")
    List<ReUserInfo> selectAllRegularUserInfo();
    //将普通用户设置为管理员
    @Update("update t_user set role='admin' where user_id=#{userId}")
    int updateRoleToAdmin(@Param("userId")Integer userId);
    //重置密码
    @Update("update t_user set password=#{passWord} where user_id=#{userId}")
    int resetReuserPassword(@Param("userId")Integer userId,@Param("passWord")String passWord);
    //删除普通用户
    @Delete("delete from t_user where user_id=#{userId}")
    int adminDeleteReUser(@Param("userId")Integer userId);
}




