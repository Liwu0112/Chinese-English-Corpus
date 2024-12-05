package com.example.controller;

import com.example.dto.*;
import com.example.service.AdministratorService;
import com.example.utils.api.BaseApiService;
import com.example.utils.api.BaseResponse;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

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

    //查看所有种类名
    @GetMapping("/selectallkind")
    public BaseResponse adminSelectAllKind() {
        List<SelectAllKindName> list = administratorService.selectAllKindName();
        return setResultSuccessData(list);
    }

    //用种类名查看其所有分类名
    @GetMapping("/selectalltype")
    public BaseResponse adminSelectAllType(@RequestParam("kindName") String kindName) {
        List<SelectTypeNames> result = administratorService.selectAllType(kindName);
        return setResultSuccessData(result);
    }

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

}
