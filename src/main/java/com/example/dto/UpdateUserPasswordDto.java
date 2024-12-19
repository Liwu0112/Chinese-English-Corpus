package com.example.dto;

import lombok.Data;

/**
 * @author liwu
 * @version 1.0
 * @date 2024/11/27
 * @注释：用于修改用户密码时接收前端传递参数
 */

@Data
public class UpdateUserPasswordDto {
    private String userName;  //用户名
    private String userOldPassword; //用户旧密码
    private String userNewPassword; //用户新密码
}
