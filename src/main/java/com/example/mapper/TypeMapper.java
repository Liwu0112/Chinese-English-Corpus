package com.example.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.dto.AdminInsertTypeDto;
import com.example.dto.AdminOperateType;
import com.example.dto.SelectTypeNames;
import com.example.entity.Type;
import org.apache.ibatis.annotations.*;

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
    @Select("select type_id from t_type where type_name=#{typeName} AND kind_id=#{kindId}")
    Integer selectTypeIdInteger(@Param("typeName")String typeName,@Param("kindId")Integer kindId);

    //查看分类总数（t_type)
    @Select("select count(1) from t_type")
    Integer countType();

    //管理员查看所有分类（分类编号，种类名，分类名）
    @Select("SELECT t_type.type_id ,t_kind.kind_name, t_type.type_name FROM t_type LEFT JOIN  t_kind ON t_type.kind_id = t_kind.kind_id")
    List<AdminOperateType> typeList();
    //修改分类
    @Update("update t_type set kind_id=#{kindId},type_name=#{typeName} where type_id=#{typeId}")
    int updateTypeInfo(@Param("typeId")Integer typeId,@Param("kindId")Integer kindId,@Param("typeName")String typeName);
    //删除分类
    @Delete("delete from t_type where type_id=#{typeId}")
    int deleteTypeByTypeId(@Param("typeId")Integer typeId);
    //新增分类
    @Insert("insert into t_type (`kind_id`,`type_name`) values (#{kindId},#{typeName})")
    int insertType(@Param("kindId")Integer kindId,@Param("typeName")String typeName);
    //查看分类是否存在
    @Select("select count(1) from t_type where type_name = #{typeName} and kind_id=#{kindId}")
    int selectTypeByTypeName(@Param("typeName")String typeName,@Param("kindId")Integer kindId);
    //使用使用typeId查询分类编号，种类名，分类名
    @Select("SELECT t_kind.kind_name, t_type.type_name FROM t_type LEFT JOIN  t_kind ON t_type.kind_id = t_kind.kind_id where type_id=#{typeId}")
    AdminInsertTypeDto typeListByTypeId(@Param("typeId")Integer typeId);
}




