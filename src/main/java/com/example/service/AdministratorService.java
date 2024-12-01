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

    //管理员查看状态为上线的语料总数
    int selectAllStatusOne();

    //查看所有状态为下线的语料总数
    int selectAllLine();
}
