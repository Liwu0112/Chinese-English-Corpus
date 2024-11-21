package com.example.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


/**
 * 
 * @TableName t_user
 */

@TableName("t_user")
@Data
public class User implements Serializable {
    /**
     * 
     */
    private Integer user_id;

    /**
     * 
     */
    private String username;

    /**
     * 
     */
    private String password;

    /**
     * 
     */
    private Date registration_date;

    /**
     * 
     */
    private Object role;

    private static final long serialVersionUID = 1L;
}