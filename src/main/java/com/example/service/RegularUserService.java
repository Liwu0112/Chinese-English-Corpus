package com.example.service;

import com.example.dto.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

    //查询所有语料
    List<CorpusDto> selectCorpus();

    //使用前端传递的语料段查询语料
    List<CorpusDto> chAndEn(String text);

    //分类查询语料
    List<CorpusDto> selectTypeCorpus(String kindName,String typeName);

    //下载语料
     void getExcel(HttpServletResponse response, List<String> listKindNames,int count) throws IOException;
}
