package com.example.service;

import com.example.dto.SelectAllKindName;
import com.example.dto.SelectTypeCorpus;
import com.example.dto.SelectTypeNames;
import com.example.dto.CorpusDto;
import java.util.List;

/**
 * @author liwu
 * @version 1.0
 * @date 2024/11/21
 * @注释：普通用户功能接口类
 */
public interface RegularUserService {
    //普通用户注册
    int regularuserEnroll(String userName,String passWord);

    //使用前端传递的语料段查询语料
    List<CorpusDto> chAndEn(String text);

    //查询所有种类名
    List<SelectAllKindName> selectKindName();

    //通过种类名产看种类名下的所有分类
    List<SelectTypeNames> selectTypeNames(String kindName);
    //分类查询语料
    List<CorpusDto> selectTypeCorpus(String kindName,String typeName);
}
