package com.example.dto;

import lombok.Data;

/**
 * @author liwu
 * @version 1.0
 * @date 2024/11/22
 * @注释：普通用户注册DTO
 * 接收前端所传递的普通用户注册时的参数
 * 参数内容为用户名和密码
 */
@Data
public class RegularUserEnrollDto {
    private String userName;
    private String passWord;
}
