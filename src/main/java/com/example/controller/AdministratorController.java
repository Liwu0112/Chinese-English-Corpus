package com.example.controller;

import com.example.dto.*;
import com.example.service.AdministratorService;
import com.example.utils.api.BaseApiService;
import com.example.utils.api.BaseResponse;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public BaseResponse adminSelectAllCorpus() {
        int result = administratorService.selectAllCorpusCount();
        return setResultSuccessData(result);
    }
    //查看上线总数
    @GetMapping("/selectallonlinecount")
    public BaseResponse adminSelectAllOnlinecount(){
        int result = administratorService.selectAllOnlineCount();
        return setResultSuccessData(result);
    }

    //查看下线总数
    @GetMapping("/selectallofflinecount")
    public BaseResponse adminSelectAllOfflineCount(){
        int result = administratorService.slectAllOfflineCount();
        return setResultSuccessData(result);
    }
    //查看种类总数
    @GetMapping("/selectallkindcount")
    public BaseResponse adminSelectAllKinds() {
        int result = administratorService.selectAllKindCount();
        return setResultSuccessData(result);
    }

    //查看分类总数
    @GetMapping("selectalltypecount")
    public BaseResponse adminSelectAllTypes() {
        int result = administratorService.selectAllTypeCount();
        return setResultSuccessData(result);
    }

    //查看种类下语料数
    @GetMapping("/selectKindcors")
    public BaseResponse adminSelectKindCors(@RequestParam("kindName") String kindName) {
        int result = administratorService.selectKindCorsCount(kindName);
        return setResultSuccessData(result);
    }

    //按种类名查询语料上线数
    @GetMapping("selectonlinebykindname")
    public BaseResponse amdinSelectOnlineByKindName(@RequestParam("kindName") String kindName) {
        int result = administratorService.selectKindOnlineCorCount(kindName);
        return setResultSuccessData(result);
    }

    //按种类名查询下线数
    @GetMapping("selectofflinebykindname")
    public BaseResponse adminSelectOffLineByKindName(@RequestParam("kindName") String kindName) {
        int result = administratorService.selectKindLineCorCount(kindName);
        return setResultSuccessData(result);
    }

    //查看普通用户数量
    @GetMapping("/selectreusercount")
    public BaseResponse adminSelectReUserCount() {
        int result = administratorService.selectReUserCount();
        return setResultSuccessData(result);
    }

    //查看所有语料
    @GetMapping("/selectallcors")
    public BaseResponse adminSelectAllCors() {
        List<AdminSelectAllCorpusDto> list = administratorService.selectAllCorpus();
        return setResultSuccessData(list);
    }

//    //查看所有种类名
//    @GetMapping("/selectallkind")
//    public BaseResponse adminSelectAllKind() {
//        List<SelectAllKindName> list = administratorService.selectAllKindName();
//        return setResultSuccessData(list);
//    }
//
//    //用种类名查看其所有分类名
//    @GetMapping("/selectalltype")
//    public BaseResponse adminSelectAllType(@RequestParam("kindName") String kindName) {
//        List<SelectTypeNames> result = administratorService.selectAllType(kindName);
//        return setResultSuccessData(result);
//    }

    //修改语料
    @PostMapping("/updatecorpus")
    public BaseResponse adminUpdateCorpus(@RequestBody AdminUpdateCorpusDto adminUpdateCorpusDto) {
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
    public BaseResponse adminDeleteCorpus(@RequestParam("corpusId") Integer corpusId) {
        int result = administratorService.deleteCorpus(corpusId);
        return setResultDb(result);
    }

    //新增单个语料
    @PostMapping("/insertonecorpus")
    public BaseResponse adminInsertOneCorpus(@RequestBody InsertCorpusDto insertCorpusDto) {
        String chineseText = insertCorpusDto.getChineseText();
        String englishText = insertCorpusDto.getEnglishText();
        String kindName = insertCorpusDto.getKindName();
        String typeName = insertCorpusDto.getTypeName();
        Object corpusStatus = insertCorpusDto.getCorpusStatus();
        String creator = insertCorpusDto.getCreator();
        int reslut = administratorService.insertOneCorpus(chineseText, englishText, kindName, typeName, corpusStatus, creator);
        if (reslut != 0) {
            return setResultDb(reslut);
        } else {
            int corpusId = administratorService.selectCorpusIdByCE(chineseText, englishText);
            return setResult(500, "", corpusId);
        }
    }

    //批量新增
    @PostMapping("/insertmorecorpus")
    public BaseResponse adminInsertMoreCorpus(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return setResultError();
        }
        return administratorService.importCorpusFromExcel(file);
    }
    //获取模板文件
    @GetMapping("/downloadTemplate")
    public ResponseEntity<Resource> downloadTemplate() {
        ByteArrayResource resource = administratorService.generateTemplate();
        if (resource == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=corpus_template.xlsx");
        headers.add(HttpHeaders.CONTENT_TYPE, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(resource.contentLength())
                .body(resource);
    }
    //查看所有分类信息
    @GetMapping("/selectalltypes")
    public BaseResponse adminSelectAllType(){
        List<AdminOperateType> list = administratorService.selectAlltype();
        return setResultSuccessData(list);
    }
    //修改分类
    @PostMapping("/updatetype")
    public BaseResponse adminUpdateType(@RequestBody AdminOperateType adminOperateType){
        int typeId = adminOperateType.getTypeId();
        String kindName = adminOperateType.getKindName();
        String typeName = adminOperateType.getTypeName();
        int result = administratorService.updateType(typeId,kindName,typeName);
        return setResultDb(result);
    }
    //删除分类
    @GetMapping("/deletetype")
    public BaseResponse adminDeleteType(@RequestParam("typeId")Integer typeId){
        int result = administratorService.deleteType(typeId);
        return setResultDb(result);
    }
    //新增分类
    @PostMapping("/inserttype")
    public BaseResponse adminInsertType(@RequestBody AdminInsertTypeDto adminInsertTypeDto){
        String kindName = adminInsertTypeDto.getKindName();
        String typeName = adminInsertTypeDto.getTypeName();
        int result = administratorService.insertType(kindName,typeName);
        return setResultDb(result);
    }
    //查看所有普通用户信息（编号，账户，注册时间，类型）
    @GetMapping("/selectallreuserinfo")
    public BaseResponse adminSelectReUserIndfo(){
        List<ReUserInfo> list = administratorService.selectAllReUserInfo();
        return setResultSuccessData(list);
    }
    //将普通用户设置为管理员
    @PostMapping("/updateuserrole")
    public BaseResponse adminUpdateUserRole(@RequestBody DisposeUserIndoById disposeUserIndoById){
        Integer userId = disposeUserIndoById.getUserId();
        int result = administratorService.updateUserRole(userId);
        return setResultDb(result);
    }
    //重置密码
    @PostMapping("/resetpassword")
    public BaseResponse adminResetPassword(@RequestBody DisposeUserIndoById disposeUserIndoById){
        Integer userId = disposeUserIndoById.getUserId();
        int result = administratorService.resetPassword(userId);
        return setResultDb(result);
    }
    //删除用户
    @PostMapping("/deleteuser")
    public BaseResponse adminDeleteUSer(@RequestBody DisposeUserIndoById disposeUserIndoById){
        Integer userId = disposeUserIndoById.getUserId();
        int result = administratorService.deleteUser(userId);
        return setResultDb(result);
    }
}
