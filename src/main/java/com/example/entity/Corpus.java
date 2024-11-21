package com.example.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


/**
 * 
 * @TableName t_corpus
 */
@TableName("t_corpus")
@Data
public class Corpus implements Serializable {
    /**
     * 
     */
    private Integer corpus_id;

    /**
     * 
     */
    private String chinese_text;

    /**
     * 
     */
    private String english_text;

    /**
     * 
     */
    private Integer type_id;

    /**
     * 
     */
    private Integer corpus_status;

    /**
     * 
     */
    private String creator;

    /**
     * 
     */
    private Date creation_time;

    private static final long serialVersionUID = 1L;
}