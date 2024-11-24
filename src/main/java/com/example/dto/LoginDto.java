package com.example.dto;

import lombok.Data;

/**
 * @author liwu
 * @version 1.0
 * @date 2024/11/21
 * @注释：公共模块中登录功能DTO
 * 接收前端传递的userName和passWord
 */
@Data
public class LoginDto {
    private String userName;
    private String passWord;
}
