package com.example.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.User;
import org.apache.ibatis.annotations.*;

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
}




