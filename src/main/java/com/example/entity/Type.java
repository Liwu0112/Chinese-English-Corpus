package com.example.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 
 * @TableName t_type
 */
@TableName("t_type")
@Data
public class Type implements Serializable {
    /**
     * 
     */
    private Integer type_id;

    /**
     * 
     */
    private String type_name;

    private static final long serialVersionUID = 1L;
}