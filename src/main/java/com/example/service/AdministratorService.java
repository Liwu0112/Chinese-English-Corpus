package com.example.service;

import com.example.dto.*;
import com.example.utils.api.BaseResponse;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.multipart.MultipartFile;

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
    //查看上线语料总数
    int selectAllOnlineCount();
    //查看下线总数
    int slectAllOfflineCount();
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
    //修改语料
    int updateCorpus(Integer corpusId,String chineseText,String englishText,String kindName,String typeName,Object corpusStatus);
    //删除语料
    int deleteCorpus(Integer corpusId);
    //新增单个语料
    int insertOneCorpus(String chineseText,String englishText,String kindName,String typeName,Object corpusStatus,String creator);
    //使用中文文本或英文文本查寻id
    int selectCorpusIdByCE(String chineseText,String englishText);
    //批量新增
    BaseResponse importCorpusFromExcel(MultipartFile file);
    //模板文件
    ByteArrayResource generateTemplate();
    //查看所有分类信息
    List<AdminOperateType> selectAlltype();
    //修改分类
    int updateType(Integer typeId,String kindName,String typeName);
    //删除分类
    int deleteType(Integer typeId);
    //新增分类
    int insertType(String kindName,String typeName);
    //查看所有普通用户信息（编号，账户，注册时间，类型）
    List<ReUserInfo> selectAllReUserInfo();
    //将普通用户设置为管理员
    int updateUserRole(Integer userId);
    //重置密码
    int resetPassword(Integer userId);
    //删除用户
    int deleteUser(Integer userId);
}
