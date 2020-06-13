package com.itwanli.controller;

import com.itwanli.entity.Admin;
import com.itwanli.result.ResultModel;
import com.itwanli.result.ResultModelTool;
import com.itwanli.service.AdminService;
import io.swagger.annotations.ApiOperation;
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

 /*   *//*登录_token登录凭证*//*
    @ApiOperation("登录_token登录凭证")
    @PostMapping("/login")
    public ResultModel login(@Validated @RequestBody LoginDTO loginDTO, BindingResult bindingResult) {
        String username = loginDTO.getUsername();
        String password = loginDTO.getPassword();
        //用户信息
        Admin admin = adminService.findByUsername(username);
        //账号不存在、密码错误
        if (admin == null || !admin.getPassword().equals(MD5Utils.code(password))) {
            ResultModel resultModel = new ResultModel();
            resultModel.setCode(4);
            return ResultModelTool.handleResultModel(resultModel);
        } else {
            //生成token，并保存到数据库
            String token = adminService.createToken(admin);
            TokenVO tokenVO = new TokenVO();
            tokenVO.setToken(token);
            ResultModel resultModel = new ResultModel();
            resultModel.setData(tokenVO);
            return ResultModelTool.handleResultModel(resultModel);
        }
    }

    *//*登出*//*
    @ApiOperation("登出")
    @PostMapping("/logout")
    public ResultModel logout(HttpServletRequest request, HttpServletResponse response) {
        //从request中取出token
        String token = TokenUtil.getRequestToken(request,response);
        adminService.logout(token);
        ResultModel resultModel = new ResultModel();
        resultModel.setMsg("登出成功");
        return ResultModelTool.handleResultModel(resultModel);
    }*/

    @ApiOperation("获取所有的管理员信息")
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
