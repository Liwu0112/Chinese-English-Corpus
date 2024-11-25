package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.Kind;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Xiao-Li
 * @description 针对表【t_kind】的数据库操作Mapper
 * @createDate 2024-11-21 22:14:35
 */
@Mapper
public interface KindMapper extends BaseMapper<Kind> {
}
