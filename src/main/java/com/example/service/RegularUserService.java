package com.example.service;

import com.example.dto.TransTextDto;
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

    //使用中文语料查询英文语料
    List<TransTextDto> chToEn(String text);

    //使用英文语料查询中文语料
    List<TransTextDto> enToCh(String text);
}
