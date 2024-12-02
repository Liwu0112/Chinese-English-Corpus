package com.example.service.lmp;

import com.example.mapper.CorpusMapper;
import com.example.mapper.KindMapper;
import com.example.mapper.TypeMapper;
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

    //管理员查看语料总数
    @Override
    public int selectAllCorpus() {
        return corpusMapper.selectAllCorpus();
    }

    //查看所有状态为上线的语料总数
    @Override
    public int selectAllStatusOne() {
        return corpusMapper.countCorpusStatusOne();
    }

    //查看所有状态为下线的语料总数
    @Override
    public int selectAllLine() {
        return corpusMapper.selectAllLineCorpus();
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

}
