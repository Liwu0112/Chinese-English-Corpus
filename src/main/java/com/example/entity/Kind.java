package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName t_kind
 */
@TableName(value ="t_kind")
@Data
public class Kind implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer kindId;

    /**
     * 
     */
    private String kindName;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}