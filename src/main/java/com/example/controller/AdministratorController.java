package com.example.controller;

import com.example.service.AdministratorService;
import com.example.utils.api.BaseApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liwu
 * @version 1.0
 * @date 2024/11/21
 * @注释：管理员控制模块
 */
@RestController
@CrossOrigin
@ResponseBody
@RequestMapping("/admin")
public class AdministratorController extends BaseApiService {
    @Autowired
    private AdministratorService administratorService;

}
