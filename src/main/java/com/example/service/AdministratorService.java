package com.example.service;

/**
 * @author liwu
 * @version 1.0
 * @date 2024/11/21
 * @注释：管理员功能接口类
 */
public interface AdministratorService {

    //管理员查看语料总数
    int selectAllCorpus();
    //查看种类总数
    int selectAllKind();
    //查看分类总数
    int selectAllType();
    //查看种类下语料数
    int selectKindCors(String kindName);
    //查看种类下语料上线数
    int selectKindOnlineCor(String kindName);
    //查看种类下语料下线数
    int selectKindLineCor(String kindName);
    //查询普通用户总数
    int selectReUserCount();
}
