package com.example.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.dto.CorpusDto;
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

    //使用前端传递的语料段查询语料
    @Select(" SELECT c.chinese_text, c.english_text, k.kind_name, t.type_name FROM t_corpus c LEFT JOIN t_type t ON c.type_id = t.type_id LEFT JOIN t_kind k ON c.kind_id = k.kind_id WHERE c.chinese_text LIKE #{text} or c.english_text like #{text} and c.corpus_status='1'")
    List<CorpusDto> selectChinesAndEnglish(@Param("text") String text);

    //普通用户分类查询
    @Select(" SELECT c.chinese_text, c.english_text, k.kind_name, t.type_name FROM t_corpus c JOIN t_kind k ON c.kind_id = k.kind_id JOIN t_type t ON c.type_id = t.type_id WHERE c.kind_id =#{kindId} AND c.type_id =#{typeId} and c.corpus_status='1'")
    List<CorpusDto> typeSelect(@Param("kindId") Integer kindId,@Param("typeId") Integer typeId);

    //查看库中所有状态为上线(corpus_status=1)的语料总数
    @Select("select count(1) from t_corpus where corpus_status='1'")
    Integer countCorpusStatusOne();

    //查看库中种类所属的语料上线数(corpus_status=1)
    @Select("select count(1) from t_corpus where kind_id=#{kind_id} and corpus_status='1'")
    Integer kindCountCors(@Param("kind_id")Integer kindId);


    //查询语料总数
    @Select("select count(1) from t_corpus")
    int selectAllCorpus();
    //查看所有状态为下线的语料总数
    @Select("select count(1) from t_corpus where corpus_status not in ('1')")
    int selectAllLineCorpus();
    //查看种类下语料数
    @Select("select count(1) from t_corpus where kind_id=#{kind_id}")
    int selectkindCor(@Param("kind_id")Integer kindId);
    //查看种类下语料下线数
    @Select("select count(1) from t_corpus where kind_id=#{kind_id} and corpus_status not in ('1')")
    int selectCorLineByKindId(@Param("kind_id")Integer kindId);
}




