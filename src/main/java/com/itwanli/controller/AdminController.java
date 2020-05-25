package com.itwanli.controller;

import com.itwanli.entity.Admin;
import com.itwanli.result.ResultModel;
import com.itwanli.result.ResultModelTool;
import com.itwanli.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin  //允许跨域访问
@RestController
@RequestMapping("/park")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/permission")
    public ResultModel getAllAdmin(){
        List<Admin> adminList = adminService.getAllAdmin();
        Map<String,List<Admin>> UserMap = new HashMap<>();
        if (adminList!=null){
            UserMap.put("admins",adminList);
        }
        ResultModel resultModel = new ResultModel();
        resultModel.setCode(0);
        resultModel.setData(UserMap);
        return ResultModelTool.handleResultModel(resultModel);
    }
}
