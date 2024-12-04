package com.example.controller;

import com.example.dto.AdminSelectAllCorpusDto;
import com.example.dto.SelectAllKindName;
import com.example.dto.SelectTypeNames;
import com.example.dto.AdminUpdateCorpusDto;
import com.example.entity.Corpus;
import com.example.service.AdministratorService;
import com.example.utils.api.BaseApiService;
import com.example.utils.api.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/selectallcorscount")
    public BaseResponse adminSelectAllCorpus(){
        int result = administratorService.selectAllCorpusCount();
        return setResultSuccessData(result);
    }
    //查看种类总数
    @GetMapping("/selectallkindcount")
    public BaseResponse adminSelectAllKinds(){
        int result = administratorService.selectAllKindCount();
        return setResultSuccessData(result);
    }
    //查看分类总数
    @GetMapping("selectalltypecount")
    public BaseResponse adminSelectAllTypes(){
        int result = administratorService.selectAllTypeCount();
        return setResultSuccessData(result);
    }
    //查看种类下语料数
    @GetMapping("/selectKindcors")
    public BaseResponse adminSelectKindCors(@RequestParam("kindName")String kindName){
        int result = administratorService.selectKindCorsCount(kindName);
        return setResultSuccessData(result);
    }
    //按种类名查询语料上线数
    @GetMapping("selectonlinebykindname")
    public BaseResponse amdinSelectOnlineByKindName(@RequestParam("kindName")String kindName){
        int result = administratorService.selectKindOnlineCorCount(kindName);
        return setResultSuccessData(result);
    }
    //按种类名查询下线数
    @GetMapping("selectofflinebykindname")
    public BaseResponse adminSelectOffLineByKindName(@RequestParam("kindName")String kindName){
        int result = administratorService.selectKindLineCorCount(kindName);
        return setResultSuccessData(result);
    }

    //查看普通用户数量
    @GetMapping("/selectreusercount")
    public BaseResponse adminSelectReUserCount(){
        int result = administratorService.selectReUserCount();
        return setResultSuccessData(result);
    }
    //查看所有语料
    @GetMapping("/selectallcors")
    public BaseResponse adminSelectAllCors(){
        List<AdminSelectAllCorpusDto> list= administratorService.selectAllCorpus();
        return setResultSuccessData(list);
    }
    //查看所有种类名
    @GetMapping("/selectallkind")
    public BaseResponse adminSelectAllKind(){
        List<SelectAllKindName> list =administratorService.selectAllKindName();
        return setResultSuccessData(list);
    }
    //用种类名查看其所有分类名
    @GetMapping("/selectalltype")
    public BaseResponse adminSelectAllType(@RequestParam("kindName")String kindName){
        List<SelectTypeNames> result = administratorService.selectAllType(kindName);
        return setResultSuccessData(result);
    }
    //修改语料
    @PostMapping("/updatecorpus")
    public BaseResponse adminUpdateCorpus(@RequestBody AdminUpdateCorpusDto adminUpdateCorpusDto){
        Integer corpusId = adminUpdateCorpusDto.getCorpusId();
        String chineseText = adminUpdateCorpusDto.getChineseText();
        String englishText = adminUpdateCorpusDto.getEnglishText();
        String kindName = adminUpdateCorpusDto.getKindName();
        String typeName = adminUpdateCorpusDto.getTypeName();
        Object corpusStatus = adminUpdateCorpusDto.getCorpusStatus();
        int result = administratorService.updateCorpus(corpusId, chineseText, englishText, kindName, typeName, corpusStatus);
        return setResultDb(result);
    }
    //删除语料
    @GetMapping("/deletecorpus")
    public BaseResponse adminDeleteCorpus(@RequestParam("corpusId")Integer corpusId){
        int result =administratorService.deleteCorpus(corpusId);
        return setResultDb(result);
    }
    //新增分类
    //新增语料

    //修改语料

    //删除语料

    //分类查看语料

    //查看所有普通

    //重置普通用户密码

    //修改用户权限

    //新增用户

    //删除用户

}
