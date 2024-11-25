package com.example.dto;

import lombok.Data;

/**
 * @author liwu
 * @version 1.0
 * @date 2024/11/24
 * @注释：定义普通用户使用中文语料查询或英文语料查询返回的数据
 * 用于映射CourpusMapper中selectEnglishTextChToEnDtos和
 *selectChineseTextEnToChDtos两个方法的返回数据
 */
@Data
public class TransTextDto {
    private String chineseText;
    private String englishText;
    private String kindName;
    private String typeName;
}
