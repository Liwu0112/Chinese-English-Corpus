package com.example.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author liwu
 * @version 1.0
 * @date 2024/12/4
 * @注释：管理员查看所有语料
 */
@Data
public class AdminSelectAllCorpusDto {
    private Integer corpusId;
    private String chineseText;
    private String englishText;
    private String kindName;
    private String typeName;
    private Object corpusStatus;
    private String creator;
    private Date creationTime;
}
