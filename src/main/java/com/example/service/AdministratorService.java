package com.example.service;

import com.example.dto.AdminSelectAllCorpusDto;
import com.example.dto.SelectAllKindName;
import com.example.dto.SelectTypeNames;
import com.example.entity.Corpus;

import java.util.List;

/**
 * @author liwu
 * @version 1.0
 * @date 2024/11/21
 * @注释：管理员功能接口类
 */
public interface AdministratorService {

    //管理员查看语料总数
    int selectAllCorpusCount();
    //查看种类总数
    int selectAllKindCount();
    //查看分类总数
    int selectAllTypeCount();
    //查看种类下语料数
    int selectKindCorsCount(String kindName);
    //查看种类下语料上线数
    int selectKindOnlineCorCount(String kindName);
    //查看种类下语料下线数
    int selectKindLineCorCount(String kindName);
    //查询普通用户总数
    int selectReUserCount();
    //查看所有语料
    List<AdminSelectAllCorpusDto> selectAllCorpus();
    //查看所有种类名
    List<SelectAllKindName> selectAllKindName();
    //查看所有分类名
    List<SelectTypeNames> selectAllType(String kindName);
}
