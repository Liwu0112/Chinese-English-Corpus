package com.example.service.lmp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.dto.*;
import com.example.entity.User;
import com.example.mapper.CorpusMapper;
import com.example.mapper.KindMapper;
import com.example.mapper.TypeMapper;
import com.example.mapper.UserMapper;
import com.example.service.RegularUserService;
import com.example.utils.MD5Utils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liwu
 * @version 1.0
 * @date 2024/11/21
 * @注释：普通用户模块实现类
 */
@Service
public class RegularUserServiceImp implements RegularUserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CorpusMapper corpusMapper;
    @Autowired
    private KindMapper kindMapper;
    @Autowired
    private TypeMapper typeMapper;

    //普通用户注册
    @Override
    public int regularuserEnroll(String userName, String passWord) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", userName);
        User user = userMapper.selectOne(queryWrapper); //查找数据库中是否存在该用户名信息
        if (user == null) {   //为空，继续操作
            MD5Utils md5Utils = new MD5Utils();
            String dbPassWord = md5Utils.md5(passWord);  //MD5加密
            User user1 = new User();
            user1.setUserName(userName);  //注册户的用户名
            user1.setPassword(dbPassWord); //注册用户的密码
            user1.setRole("regular_user"); //注册用户为普通用户，所以角色值为regular_user
            return userMapper.insert(user1);
        } else {
            return 0; //不为空，返回0
        }
    }

    //查询所有语料
    @Override
    public List<CorpusDto> selectCorpus() {
        return corpusMapper.reUserSelectCorpusAll();
    }

    //使用前端传递的语料段查询语料
    @Override
    public List<CorpusDto> chAndEn(String text) {
        String likeText = "%" + text + "%";  //模糊查询需要拼接
        return corpusMapper.selectChinesAndEnglish(likeText);  //返回对应数据
    }

    //分类查询
    @Override
    public List<CorpusDto> selectTypeCorpus(String kindName, String typeName) {
        Integer kindId = kindMapper.selectKindIdByKindNameInteger(kindName);
        Integer typeId = typeMapper.selectTypeIdInteger(typeName, kindId);
        return corpusMapper.typeSelect(kindId, typeId);
    }

    // 下载语料（修正 NullPointerException）
    public void getExcel(HttpServletResponse response,List<String> listKindNames,int count) throws IOException {
        List<CorpusDto> corpusDtoList = null;

        if (count == 10) {
            corpusDtoList = corpusMapper.reUserSelectCorpusAll();
        } else {
            corpusDtoList = new ArrayList<>(); // 先初始化列表，避免空指针异常
            for (String kindName : listKindNames) {
                List<CorpusDto> tempList = corpusMapper.reUserDownLoad(kindName);
                corpusDtoList.addAll(tempList); // 把查询的集合全部添加到 corpusDtoList
            }
        }
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("语料");
        // 创建表头
        Row headerRow = sheet.createRow(0);
        String[] headerArray = {"中文文本", "英文文本", "种类", "分类"};
        for (int i = 0; i < headerArray.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headerArray[i]);
        }
        // 填充数据
        int rowNum = 1;
        for (CorpusDto item : corpusDtoList) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(item.getChineseText());
            row.createCell(1).setCellValue(item.getEnglishText());
            row.createCell(2).setCellValue(item.getKindName());
            row.createCell(3).setCellValue(item.getTypeName());
        }
        // 设置 HTTP 响应头
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=corpus.xlsx");
        // 输出 Excel 文件
        workbook.write(response.getOutputStream());
        workbook.close();
    }
}
