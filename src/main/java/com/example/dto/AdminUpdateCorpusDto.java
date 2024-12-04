package com.example.dto;

import lombok.Data;

/**
 * @author liwu
 * @version 1.0
 * @date 2024/12/4
 * @注释：修改语料时前端传递的参数
 */
@Data
public class AdminUpdateCorpusDto {
    private Integer corpusId;
    private String chineseText;
    private String englishText;
    private String kindName;
    private String typeName;
    private Object corpusStatus;

}
