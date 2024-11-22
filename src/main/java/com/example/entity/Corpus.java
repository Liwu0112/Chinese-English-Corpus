package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName t_corpus
 */
@TableName(value ="t_corpus")
@Data
public class Corpus implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer corpusId;

    /**
     * 
     */
    private String chineseText;

    /**
     * 
     */
    private String englishText;

    /**
     * 
     */
    private Integer typeId;

    /**
     * 
     */
    private Integer corpusStatus;

    /**
     * 
     */
    private String creator;

    /**
     * 
     */
    private Date creationTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}