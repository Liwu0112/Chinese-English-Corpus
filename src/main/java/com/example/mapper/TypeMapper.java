package com.example.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.dto.SelectTypeNames;
import com.example.entity.Type;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author Xiao-Li
* @description 针对表【t_type】的数据库操作Mapper
* @createDate 2024-11-21 22:14:35
*/
@Mapper
public interface TypeMapper extends BaseMapper<Type> {
    //使用kind_id查询其所有的分类名
    @Select("select type_name from t_type where kind_id=#{kindId}")
    List<SelectTypeNames> selectTypeNamesByKId(@Param("kindId")Integer kindId);
    //使用type_name查询type_id
    @Select("select type_id from t_type where type_name=#{typeName}")
    Integer selectTypeIdInteger(@Param("typeName")String typeName);
}




