package com.example.dto;

import lombok.Data;

/**
 * @author liwu
 * @version 1.0
 * @date 2024/11/28
 * @注释：使用kind_id查询其所属的所有type_name
 */
@Data
public class SelectTypeNames {
    private String typeName;
}
