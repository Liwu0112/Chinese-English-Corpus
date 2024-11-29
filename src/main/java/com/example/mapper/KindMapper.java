package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.dto.SelectAllKindName;
import com.example.entity.Kind;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Xiao-Li
 * @description 针对表【t_kind】的数据库操作Mapper
 * @createDate 2024-11-21 22:14:35
 */
@Mapper
public interface KindMapper extends BaseMapper<Kind> {

    //查询所有种类名
    @Select("select kind_name from t_kind ")
    List<SelectAllKindName> selectAllKindName();

    //通过kind_name查询kind_id
    @Select("select kind_id from t_kind where kind_name=#{kindName}")
    Integer selectKindIdByKindNameInteger(@Param("kindName")String kindName);

    //查看种类总数（t_kind)
    @Select("select count(1) from t_kind")
    Integer countKind();
}
