package com.example.dto;

import lombok.Data;

/**
 * @author liwu
 * @version 1.0
 * @date 2024/11/28
 * @注释：分类查询Dto，用于接收前端传递的种类名和分类名
 */
@Data
public class SelectTypeCorpus {
    private String kindName;
    private String typeName;
}
