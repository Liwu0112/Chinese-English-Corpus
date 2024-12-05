package com.example.service.lmp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.dto.*;
import com.example.mapper.CorpusMapper;
import com.example.mapper.KindMapper;
import com.example.mapper.TypeMapper;
import com.example.mapper.UserMapper;
import com.example.service.AdministratorService;
import com.example.utils.api.BaseResponse;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
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
 * @注释：管理员功能实现类
 */
@Service
public class AdministratorServiceImp implements AdministratorService {

    @Autowired
    private CorpusMapper corpusMapper;
    @Autowired
    private KindMapper kindMapper;
    @Autowired
    private TypeMapper typeMapper;
    @Autowired
    private UserMapper userMapper;

    //管理员查看语料总数
    @Override
    public int selectAllCorpusCount() {
        return corpusMapper.selectAllCorpusCount();
    }

    //查看种类总数
    @Override
    public int selectAllKindCount() {
        return kindMapper.countKind();
    }

    //查看分类总数
    @Override
    public int selectAllTypeCount() {
        return typeMapper.countType();
    }

    //查看种类下语料数
    @Override
    public int selectKindCorsCount(String kindName) {
        int kindId = kindMapper.selectKindIdByKindNameInteger(kindName);
        return corpusMapper.selectkindCor(kindId);
    }

    //查看种类下语料上线数
    @Override
    public int selectKindOnlineCorCount(String kindName) {
        int kindId = kindMapper.selectKindIdByKindNameInteger(kindName);
        return corpusMapper.kindCountCors(kindId);
    }

    //查看种类下语料下线数
    @Override
    public int selectKindLineCorCount(String kindName) {
        int kindId = kindMapper.selectKindIdByKindNameInteger(kindName);
        return corpusMapper.selectCorLineByKindId(kindId);
    }

    //查询普通用户总数
    @Override
    public int selectReUserCount() {
        return userMapper.selectAllReUser();
    }

    //查看所有语料
    @Override
    public List<AdminSelectAllCorpusDto> selectAllCorpus() {
        return corpusMapper.selectAllCorpus();
    }

    //查看所有种类名
    @Override
    public List<SelectAllKindName> selectAllKindName() {
        return kindMapper.selectAllKindName();
    }

    //查看所有分类名
    @Override
    public List<SelectTypeNames> selectAllType(String kindName) {
        int id = kindMapper.selectKindIdByKindNameInteger(kindName);
        return typeMapper.selectTypeNamesByKId(id);
    }

    //修改语料
    @Override
    public int updateCorpus(Integer corpusId, String chineseText, String englishText, String kindName, String typeName, Object corpusStatus) {
        int kindId = kindMapper.selectKindIdByKindNameInteger(kindName);
        int typeId = typeMapper.selectTypeIdInteger(typeName);
        int result = corpusMapper.updateCorpus(corpusId, chineseText, englishText, kindId, typeId, corpusStatus);
        return result;
    }

    //删除语料
    @Override
    public int deleteCorpus(Integer corpusId) {
        return corpusMapper.deleteCorpus(corpusId);
    }

    //新增单个语料
    @Override
    public int insertOneCorpus(String chineseText, String englishText, String kindName, String typeName, Object corpusStatus, String creator) {
        int a = corpusMapper.selectCountByChAndEn(chineseText, englishText);
        if (a == 0) {
            int kindId = kindMapper.selectKindIdByKindNameInteger(kindName);
            int typeId = typeMapper.selectTypeIdInteger(typeName);
            int result = corpusMapper.adminInsertCorpus(chineseText, englishText, kindId, typeId, corpusStatus, creator);
            return result;
        } else {
            return 0;
        }
    }

    //使用中文文本或英文文本查寻id
    @Override
    public int selectCorpusIdByCE(String chineseText, String englishText) {
        return corpusMapper.selectCorpusIdByChAndEn(chineseText, englishText);
    }

    //批量新增
    @Override
    public BaseResponse importCorpusFromExcel(MultipartFile file) {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        try (InputStream inputStream = file.getInputStream()) {
            // 创建工作簿
            Workbook workbook;
            if (file.getOriginalFilename().endsWith(".xlsx")) {
                workbook = new XSSFWorkbook(inputStream);
            } else if (file.getOriginalFilename().endsWith(".xls")) {
                workbook = new HSSFWorkbook(inputStream);
            } else {
                return new BaseResponse(500, "不支持的文件格式", null);
            }
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            // 跳过表头行
            if (rowIterator.hasNext()) {
                rowIterator.next();
            }
            // 处理每一行数据
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                processExcelRow(row, executor);
            }
            // 关闭线程池
            executor.shutdown();
            try {
                if (!executor.awaitTermination(600, TimeUnit.SECONDS)) {
                    executor.shutdownNow();
                    return new BaseResponse(500, "导入超时", null);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return new BaseResponse(500, "导入被中断", null);
            }

            return new BaseResponse(200, "导入成功", null);

        } catch (IOException e) {
            e.printStackTrace();
            return new BaseResponse(500, "导入失败", null);
        }
    }

    /**
     * 处理Excel的每一行数据
     */
    private void processExcelRow(Row row, ExecutorService executor) {
        // 读取每一列的数据
        String chineseText = getCellStringValue(row.getCell(0));
        String englishText = getCellStringValue(row.getCell(1));
        String kindName = getCellStringValue(row.getCell(2));
        String typeName = getCellStringValue(row.getCell(3));
        Object corpusStatus = row.getCell(4) != null ? row.getCell(4).getStringCellValue() : 0;
        String creator = getCellStringValue(row.getCell(5));

        // 确保必要字段不为空
        if (!chineseText.isEmpty() && !englishText.isEmpty() &&
                !kindName.isEmpty() && !typeName.isEmpty()) {

            // 提交任务到线程池
            executor.submit(() -> insertOneCorpus(
                    chineseText,
                    englishText,
                    kindName,
                    typeName,
                    corpusStatus,
                    creator
            ));
        }
    }

    /**
     * 安全地获取单元格的字符串值
     */
    private String getCellStringValue(Cell cell) {
        return cell != null ? cell.getStringCellValue() : "";
    }

    //获取模板文件
    @Override
    public ByteArrayResource generateTemplate() {
        try {
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("语料模板");

            // 创建文本格式的单元格样式
            CellStyle textStyle = workbook.createCellStyle();
            DataFormat format = workbook.createDataFormat();
            textStyle.setDataFormat(format.getFormat("@"));  // @ 表示文本格式

            Row headerRow = sheet.createRow(0);
            String[] headers = {"中文文本", "英文文本", "种类", "分类", "状态", "创建者"};

            for (int i = 0; i < headers.length; i++) {
                sheet.setColumnWidth(i, 20 * 256);
                // 设置整列的默认格式为文本
                sheet.setDefaultColumnStyle(i, textStyle);

                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);

                CellStyle headerStyle = workbook.createCellStyle();
                headerStyle.cloneStyleFrom(textStyle);  // 继承文本格式
                Font font = workbook.createFont();
                font.setBold(true);
                headerStyle.setFont(font);
                headerStyle.setAlignment(HorizontalAlignment.CENTER);
                cell.setCellStyle(headerStyle);
            }

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            workbook.close();
            return new ByteArrayResource(outputStream.toByteArray());

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    //查看所有分类信息
    @Override
    public List<AdminOperateType> selectAlltype() {
        return typeMapper.typeList();
    }
    //修改分类
    @Override
    public int updateType(Integer typeId, String kindName, String typeName) {
        int kindId = kindMapper.selectKindIdByKindNameInteger(kindName);
        int count = typeMapper.selectTypeByTypeName(typeName);
        AdminInsertTypeDto adminInsertTypeDto = typeMapper.typeListByTypeId(typeId);
        String dbKindName = adminInsertTypeDto.getKindName();
        String dbTYpeName = adminInsertTypeDto.getTypeName();
        if (count == 0 || !dbKindName.equals(kindName) || !dbTYpeName.equals(typeName)) {
            corpusMapper.updateCorpusKindId(kindId, typeId);
            return typeMapper.updateTypeInfo(typeId, kindId, typeName);
        }
        return 0;
    }
    //删除分类
    @Override
    public int deleteType(Integer typeId) {
        int count = corpusMapper.selectCountByTypeId(typeId);
        if (count==0){
            return typeMapper.deleteTypeByTypeId(typeId);
        }
        return 0;
    }
    //新增分类
    @Override
    public int insertType(String kindName, String typeName) {
        int kindId = kindMapper.selectKindIdByKindNameInteger(kindName);
        int count = typeMapper.selectTypeByTypeName(typeName);
        if (count==0){
            return typeMapper.insertType(kindId,typeName);
        }
        return 0;
    }
}

