package com.example.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.dto.ChToEnDto;
import com.example.entity.Corpus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


/**
* @author Xiao-Li
* @description 针对表【t_corpus】的数据库操作Mapper
* @createDate 2024-11-21 22:14:35
* @Entity generator.domain.TCorpus
*/
@Mapper
public interface CorpusMapper extends BaseMapper<Corpus> {

    //查询语料中文对应的英文，存在一对多的情况
    //这里使用使用模糊查询
    @Select("select english_text from t_corpus where chinese_text like #{chText}")
    List<ChToEnDto> selectEnglishTextChToEnDtos(@Param("chText") String chText);
}




