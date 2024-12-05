package com.example.dto;

import lombok.Data;

/**
 * @author liwu
 * @version 1.0
 * @date 2024/12/5
 * @注释：接受前端新增语料传递的数据
 */
@Data
public class InsertCorpusDto {
    private String chineseText;
    private String englishText;
    private String kindName;
    private String typeName;
    private Object corpusStatus;
    private String creator;
}
