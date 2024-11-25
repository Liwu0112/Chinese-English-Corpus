package com.example.dto;

import lombok.Data;

/**
 * @author liwu
 * @version 1.0
 * @date 2024/11/25
 * @注释：用于接收用户更改用户名时，前端传递的旧用户名和新用户名
 */
@Data
public class UpdateUserNameDto {
    private String userName;  //旧用户名
    private String userNewName; //新用户名
}
