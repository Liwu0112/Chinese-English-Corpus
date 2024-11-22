package com.example.dto;

/**
 * @author liwu
 * @version 1.0
 * @date 2024/11/21
 * @注释：公共模块中登录功能DTO
 * 接收前端传递的userName和passWord
 */
public class LoginDto {
    private String userName;
    private String passWord;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
