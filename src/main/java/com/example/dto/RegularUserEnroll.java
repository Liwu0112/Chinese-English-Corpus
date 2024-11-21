package com.example.dto;

/**
 * @author liwu
 * @version 1.0
 * @date 2024/11/22
 * @注释：普通用户注册DTO
 * 接收前端所传递的普通用户注册时的参数
 * 参数内容为用户名和密码
 */
public class RegularUserEnroll {
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
