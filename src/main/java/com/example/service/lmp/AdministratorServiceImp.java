package com.example.service.lmp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.dto.AdminSelectAllCorpusDto;
import com.example.dto.SelectAllKindName;
import com.example.dto.SelectTypeNames;
import com.example.entity.Corpus;
import com.example.mapper.CorpusMapper;
import com.example.mapper.KindMapper;
import com.example.mapper.TypeMapper;
import com.example.mapper.UserMapper;
import com.example.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public int selectAllTypeCount(){
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
    public int updateCorpus(Integer corpusId,String chineseText, String englishText, String kindName, String typeName, Object corpusStatus) {
    int kindId = kindMapper.selectKindIdByKindNameInteger(kindName);
    int typeId = typeMapper.selectTypeIdInteger(typeName);
    int result = corpusMapper.updateCorpus(corpusId,chineseText,englishText,kindId,typeId,corpusStatus);
    return result;
    }

    @Override
    public int deleteCorpus(Integer corpusId) {
        return corpusMapper.deleteCorpus(corpusId);
    }


}
