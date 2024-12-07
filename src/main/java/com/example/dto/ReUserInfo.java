package com.example.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author liwu
 * @version 1.0
 * @date 2024/12/7
 * @注释：管理员查询普通用户信息时使用，包括用户编号，账户名，注册时间，类型
 */
@Data
public class ReUserInfo {
    private Integer userId;
    private String userName;
    private Date registrationDate;
    private Object role;
}
