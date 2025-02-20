package com.example.controller;
import com.example.dto.*;
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
@CrossOrigin(origins = "http://localhost:8080", allowCredentials = "true")
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


    //查询所有语料
    @GetMapping("/selectallcorpus")
    public BaseResponse reUserSelectAllCorpus(){
        List<CorpusDto> list = regularUserService.selectCorpus();
        return setResultSuccessData(list);
    }

    /*使用前端传递的语料段查询语料
     */
    @PostMapping("/translationbytext")
    public BaseResponse translationChText(@RequestParam("text") String text) {
        List<CorpusDto> list = regularUserService.chAndEn(text);
        if (list != null && !list.isEmpty()) {
            // 有数据时，返回成功
            return setResultSuccessData(list);
        } else {
            // list 为 null 的情况,存在空集合情况
            return setResultError();
        }
    }

//    //查看所有种类名
//    @GetMapping("/selectallkindname")
//    public BaseResponse reUserSelectAllKinds() {
//        List<SelectAllKindName> list = regularUserService.selectKindName();
//        return setResultSuccessData(list);
//    }
//
//    //通过种类名产看种类名下的所有分类
//    @PostMapping("/selecttnbykn")
//    public BaseResponse reUserSelectTnByKn(@RequestParam("kindName") String kindName) {
//        List<SelectTypeNames> list = regularUserService.selectTypeNames(kindName);
//        return setResultSuccessData(list);
//    }

    //查看分类下的所有语料
    @PostMapping("/selecttypecorpus")
    public BaseResponse reUserSelectTypeCorpus(@RequestBody SelectTypeCorpus selectTypeCorpus) {
        String kindName = selectTypeCorpus.getKindName();
        String typeName = selectTypeCorpus.getTypeName();
        List<CorpusDto> list = regularUserService.selectTypeCorpus(kindName,typeName);
        if (list != null && !list.isEmpty()) {
            // 有数据时，返回成功
            return setResultSuccessData(list);
        } else {
            // list 为 null 的情况,存在空集合情况
            return setResultError();
        }
    }

    //查看库中所有状态为上线(corpus_status=1)的语料总数
    @GetMapping("/reselectcountcorpus")
    public BaseResponse reSelectCountCorpus(){
        int result = regularUserService.selectCountCorpusone();
        return setResultSuccessData(result);
    }


    //查看种类总数（t_kind)
    @GetMapping("/reselectcountkind")
    public BaseResponse reSelectCountKind(){
        int result = regularUserService.selectCountKind();
        return setResultSuccessData(result);
    }

    //查看分类总数（t_type)
    @GetMapping("/reselectcounttype")
    public BaseResponse reSelectCountType(){
        int result = regularUserService.selectCountType();
        return setResultSuccessData(result);
    }

    //查看库中种类所属的语料总数且状态为上线(corpus_status=1)
    @GetMapping("/rescorpusbykindname")
    public  BaseResponse resCorpusByKindName(@RequestParam("kindName") String kindName){
        int result = regularUserService.kindToCorpus(kindName);
        return setResultSuccessData(result);
    }
}
