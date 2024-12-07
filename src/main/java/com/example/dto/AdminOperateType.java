package com.example.dto;

import lombok.Data;

/**
 * @author liwu
 * @version 1.0
 * @date 2024/12/5
 * @注释：传递所有分类，包括分类编号，分类所属种类名，分类名
 */
@Data
public class AdminOperateType {
    private Integer typeId;
    private String kindName;
    private String typeName;
}
