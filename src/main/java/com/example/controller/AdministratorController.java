package com.example.controller;

import com.example.service.AdministratorService;
import com.example.utils.api.BaseApiService;
import com.example.utils.api.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author liwu
 * @version 1.0
 * @date 2024/11/21
 * @注释：管理员控制模块
 */
@RestController
@ResponseBody
@RequestMapping("/admin")
public class AdministratorController extends BaseApiService {
    @Autowired
    private AdministratorService administratorService;

    //查看语料总数
    @GetMapping("/selectallcors")
    public BaseResponse adminSelectAllCorpus(){
        int result = administratorService.selectAllCorpus();
        return setResultSuccessData(result);
    }
    //查看种类总数
    @GetMapping("/selectallkind")
    public BaseResponse adminSelectAllKinds(){
        int result = administratorService.selectAllKind();
        return setResultSuccessData(result);
    }
    //查看分类总数
    @GetMapping("selectalltype")
    public BaseResponse adminSelectAllTypes(){
        int result = administratorService.selectAllType();
        return setResultSuccessData(result);
    }
    //查看种类下语料数
    @GetMapping("/selectKindcors")
    public BaseResponse adminSelectKindCors(@RequestParam("kindName")String kindName){
        int result = administratorService.selectKindCors(kindName);
        return setResultSuccessData(result);
    }
    //按种类名查询语料上线数
    @GetMapping("selectonlinebykindname")
    public BaseResponse amdinSelectOnlineByKindName(@RequestParam("kindName")String kindName){
        int result = administratorService.selectKindOnlineCor(kindName);
        return setResultSuccessData(result);
    }
    //按种类名查询下线数
    @GetMapping("selectofflinebykindname")
    public BaseResponse adminSelectOffLineByKindName(@RequestParam("kindName")String kindName){
        int result = administratorService.selectKindLineCor(kindName);
        return setResultSuccessData(result);
    }

    //查看普通用户数量
    @GetMapping("/selectreusercount")
    public BaseResponse adminSelectReUserCount(){
        int result = administratorService.selectReUserCount();
        return setResultSuccessData(result);
    }
    //新增分类

    //新增语料

    //修改语料

    //删除语料

    //查看所有语料

    //分类查看语料

    //查看所有普通

    //重置普通用户密码

    //修改用户权限

    //新增用户

    //删除用户

}
