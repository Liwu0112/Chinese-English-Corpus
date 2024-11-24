package com.example.dto;

import lombok.Data;

/**
 * @author liwu
 * @version 1.0
 * @date 2024/11/24
 * @注释：定义普通用户中中译英的英文文本
 * 映射CorpusMapper中selectEnglishTextChToEnDtos方法查询的english_text字段
 */
@Data
public class ChToEnDto {
    String englishText;
}
