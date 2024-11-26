package com.example.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.dto.TransTextDto;
import com.example.entity.Corpus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import java.util.List;


/**
* @author Xiao-Li
* @description 针对表【t_corpus】的数据库操作Mapper
* @createDate 2024-11-21 22:14:35
*/
@Mapper
public interface CorpusMapper extends BaseMapper<Corpus> {

    /*使用前端传递的语料段查询语料
    使用模糊查询
     */
    @Select(" SELECT c.chinese_text, c.english_text, k.kind_name, t.type_name FROM t_corpus c LEFT JOIN t_type t ON c.type_id = t.type_id LEFT JOIN t_kind k ON c.kind_id = k.kind_id WHERE c.chinese_text LIKE #{text} or c.english_text like #{text}")
    List<TransTextDto> selectChinesAndEnglish(@Param("text") String text);
}




