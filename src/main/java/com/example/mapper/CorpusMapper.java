package com.example.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.dto.AdminSelectAllCorpusDto;
import com.example.dto.CorpusDto;
import com.example.entity.Corpus;
import org.apache.ibatis.annotations.*;

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
    int selectAllCorpusCount();
    //查看种类下语料数
    @Select("select count(1) from t_corpus where kind_id=#{kind_id}")
    int selectkindCor(@Param("kind_id")Integer kindId);
    //查看种类下语料下线数
    @Select("select count(1) from t_corpus where kind_id=#{kind_id} and corpus_status not in ('1')")
    int selectCorLineByKindId(@Param("kind_id")Integer kindId);
    //查询所有语料
    @Select("SELECT c.corpus_id,c.chinese_text, c.english_text, k.kind_name, t.type_name ,c.corpus_status,c.creator,c.creation_time FROM t_corpus c LEFT JOIN t_type t ON c.type_id = t.type_id LEFT JOIN t_kind k ON c.kind_id = k.kind_id ")
    List<AdminSelectAllCorpusDto> selectAllCorpus();
    //查询是否存在当前语料
    @Select("select * from t_corpus where chinese_text=#{chineseText} and english_text=#{englishText}")
    List<Corpus> selectThisCorpus(@Param("chineseText")String chineseText,@Param("englishText")String englishText);
    //修改语料
    @Update("update t_corpus set chinese_text=#{chineseText},english_text=#{englishText},kind_id=#{kindId},type_id=#{typeId},corpus_status=#{corpusStatus} where corpus_id=#{corpusId}")
    int updateCorpus(@Param("corpusId")Integer corpusId,@Param("chineseText")String chineseText,@Param("englishText")String englishText,@Param("kindId")int kindId,@Param("typeId")int typeId,@Param("corpusStatus")Object corpusStatus);
    //删除语料
    @Delete("delete from t_corpus where corpus_id = #{corpusId}")
    int deleteCorpus(@Param("corpusId")Integer corpusId);
    //根据中文文本或者英文文本查询新增语料Id
    @Select("select corpus_id from t_corpus where chinese_text=#{chineseText} and english_text=#{englishText}")
    int selectCorpusIdByChAndEn(@Param("chineseText")String chineseText,@Param("englishText")String englishText);
    //查询当前条件为中文文本和英文文本，返回为当前中文文本和英文文本在数据库中的总数
    @Select("select count(1) from t_corpus where chinese_text=#{chineseText} and english_text=#{englishText}")
    int selectCountByChAndEn(@Param("chineseText")String chineseText,@Param("englishText")String englishText);
    //新增语料
    @Insert("insert into t_corpus ( `chinese_text`, `english_text`, `kind_id`, `type_id`, `corpus_status`, `creator`) values (#{chinseText},#{englishText},#{kindId},#{typeId},#{corpusStatus},#{creator})")
    int adminInsertCorpus(@Param("chinseText")String chineseText,@Param("englishText")String englishText,@Param("kindId")Integer kindId,@Param("typeId")Integer typeId,@Param("corpusStatus") Object corpusStatus,@Param("creator")String creator);
}




