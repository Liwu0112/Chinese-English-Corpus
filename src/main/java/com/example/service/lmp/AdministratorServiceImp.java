package com.example.service.lmp;

import com.example.mapper.CorpusMapper;
import com.example.mapper.KindMapper;
import com.example.mapper.TypeMapper;
import com.example.mapper.UserMapper;
import com.example.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public int selectAllCorpus() {
        return corpusMapper.selectAllCorpus();
    }
    //查看种类总数
    @Override
    public int selectAllKind() {
        return kindMapper.countKind();
    }
    //查看分类总数
    @Override
    public int selectAllType(){
        return typeMapper.countType();
    }

    //查看种类下语料数
    @Override
    public int selectKindCors(String kindName) {
        int kindId = kindMapper.selectKindIdByKindNameInteger(kindName);
        return corpusMapper.selectkindCor(kindId);
    }

    //查看种类下语料上线数
    @Override
    public int selectKindOnlineCor(String kindName) {
        int kindId = kindMapper.selectKindIdByKindNameInteger(kindName);
        return corpusMapper.kindCountCors(kindId);
    }

    //查看种类下语料下线数
    @Override
    public int selectKindLineCor(String kindName) {
        int kindId = kindMapper.selectKindIdByKindNameInteger(kindName);
        return corpusMapper.selectCorLineByKindId(kindId);
    }
    //查询普通用户总数
    @Override
    public int selectReUserCount() {
        return userMapper.selectAllReUser();
    }


}
