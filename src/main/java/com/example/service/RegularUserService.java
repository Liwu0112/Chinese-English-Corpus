package com.example.service;

import com.example.dto.ChToEnDto;
import com.example.entity.Corpus;

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

    //中译英
    List<ChToEnDto> chToEn(String text);
}
