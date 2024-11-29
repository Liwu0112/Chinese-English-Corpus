package com.example.service.lmp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.dto.SelectAllKindName;
import com.example.dto.SelectTypeCorpus;
import com.example.dto.SelectTypeNames;
import com.example.dto.CorpusDto;
import com.example.entity.User;
import com.example.mapper.CorpusMapper;
import com.example.mapper.KindMapper;
import com.example.mapper.TypeMapper;
import com.example.mapper.UserMapper;
import com.example.service.RegularUserService;
import com.example.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    //使用前端传递的语料段查询语料
    @Override
    public List<CorpusDto> chAndEn(String text) {
        String likeText = "%" + text + "%";  //模糊查询需要拼接
        return corpusMapper.selectChinesAndEnglish(likeText);  //返回对应数据
    }

    //查询所有种类（kind）名
    @Override
    public List<SelectAllKindName> selectKindName(){
        return kindMapper.selectAllKindName();
    }

    //通过种类名产看种类名下的所有分类
    @Override
    public List<SelectTypeNames>  selectTypeNames(String kindName){
        Integer kindId = kindMapper.selectKindIdByKindNameInteger(kindName);
        return typeMapper.selectTypeNamesByKId(kindId);
    }

    //分类查询
    @Override
    public List<CorpusDto> selectTypeCorpus(String kindName, String typeName) {
        Integer kindId = kindMapper.selectKindIdByKindNameInteger(kindName);
        Integer typeId = typeMapper.selectTypeIdInteger(typeName);
        return corpusMapper.typeSelect(kindId,typeId);
    }

    //查看库中所有状态为上线(corpus_status=1)的语料总数
    @Override
    public int selectCountCorpusone() {
        return corpusMapper.countCorpusStatusOne();
    }

    //查看种类总数（t_kind)
    @Override
    public int selectCountKind() {
        return kindMapper.countKind();
    }

    //查看分类总数（t_type)
    @Override
    public int selectCountType() {
        return typeMapper.countType();
    }

    //查看库中种类所属的语料总数且状态为上线(corpus_status=1)
    @Override
    public int kindToCorpus(String kindName) {
        Integer kindId = kindMapper.selectKindIdByKindNameInteger(kindName);
        return corpusMapper.kindCountCors(kindId);
    }
}
