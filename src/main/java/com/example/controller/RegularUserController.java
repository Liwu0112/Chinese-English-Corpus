package com.example.controller;

import com.example.dto.RegularUserEnrollDto;
import com.example.dto.TransTextDto;
import com.example.service.RegularUserService;
import com.example.utils.api.BaseApiService;
import com.example.utils.api.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author liwu
 * @version 1.0
 * @date 2024/11/21
 * @注释：普通用户控制类
 */
@RestController
@CrossOrigin
@ResponseBody
@RequestMapping("/regularuser")
public class RegularUserController extends BaseApiService {
    @Autowired
    private RegularUserService regularUserService;

    //普通用户注册
    @PostMapping("/enroll")
    public BaseResponse regularuserEnroll(@RequestBody RegularUserEnrollDto regularUserEnroll) {
        String userName = regularUserEnroll.getUserName(); //获取注册时前端传递的用户名和密码
        String passWord = regularUserEnroll.getPassWord();
        int a = regularUserService.regularuserEnroll(userName, passWord); //调用注册方法
        return setResultDb(a); //返回状态码为200，注册成功；返回状态码为500，注册失败，该用户已经注册
    }

    /*使用中文语料查询英文语料
    返回数据为数据库中所有语料的中文文本包含前端传递text的英文文本
    具体SQL语句见CorpusMapper的selectEnglishTextChToEnDtos方法
     */
    @PostMapping("/translationch")
    public BaseResponse translationChText(@RequestParam("text") String text) {
        List<TransTextDto> list = regularUserService.chToEn(text);  //查询英文集合方法
        if (list != null && !list.isEmpty()) {
            // 有数据时，返回成功
            return setResultSuccessData(list);
        } else {
            // list 为 null 的情况,存在空集合情况
            return setResultError();
        }
    }

    /*使用英文语料查询中文语料
    返回数据为数据库中所有语料的英文文本包含前端传递text的中文文本
    具体SQL语句见CorpusMapper的selectChineseTextEnToChDtos方法
     */
    @PostMapping("/translationen")
    public BaseResponse translationEnText(@RequestParam("text") String text) {
        List<TransTextDto> list = regularUserService.enToCh(text);  //查询中文集合方法
        if (list != null && !list.isEmpty()) {
            // 有数据时，返回成功
            return setResultSuccessData(list);
        } else {
            // list 为 null 的情况,存在空集合情况
            return setResultError();
        }
    }
    //按分类查找
}
