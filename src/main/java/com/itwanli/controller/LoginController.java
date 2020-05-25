package com.itwanli.controller;

import com.itwanli.entity.Admin;
import com.itwanli.entity.User;
import com.itwanli.result.ResultModel;
import com.itwanli.result.ResultModelTool;
import com.itwanli.service.AdminService;
import com.itwanli.service.CarOwnerService;
import com.itwanli.service.UserService;
import com.itwanli.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@CrossOrigin  //允许跨域访问
@RestController
//@RequestMapping("/park")
public class LoginController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserService userService;

    @Autowired
    private CarOwnerService carOwnerService;

    @PostMapping("/park/login")
    public ResultModel login(@RequestBody Admin admin, HttpSession session){
        Admin a = adminService.checkAdmin(admin);
        if(a != null){
            admin.setPassword(MD5Utils.code(admin.getPassword()));
            session.setAttribute("admin",a);
            ResultModel resultModel = new ResultModel();
            resultModel.setCode(2);
            resultModel.setData(a);
            return ResultModelTool.handleResultModel(resultModel);
        }else {
            ResultModel resultModel = new ResultModel();
            resultModel.setCode(4);
            return ResultModelTool.handleResultModel(resultModel);
        }
    }

    @GetMapping("/logout")
    public ResultModel logout(HttpSession session){
        session.removeAttribute("admin");
        ResultModel resultModel = new ResultModel();
        resultModel.setCode(6);
        return ResultModelTool.handleResultModel(resultModel);
    }

    @PostMapping("/android/userlogin")
    public ResultModel userLogin(@RequestBody User user){
        User u = userService.login(user.getUsername(),user.getPassword());
        if(u != null){
            ResultModel resultModel = new ResultModel();
            resultModel.setCode(2);
            resultModel.setData(u);
            return ResultModelTool.handleResultModel(resultModel);
        }else {
            ResultModel resultModel = new ResultModel();
            resultModel.setCode(4);
            return ResultModelTool.handleResultModel(resultModel);
        }
    }

    @PostMapping("/android/carownerlogin")
    public ResultModel carOwnerLogin(@RequestBody User user){
        User u = userService.login(user.getUsername(),user.getPassword());
        if(u != null){
            ResultModel resultModel = new ResultModel();
            resultModel.setCode(2);
            resultModel.setData(u);
            return ResultModelTool.handleResultModel(resultModel);
        }else {
            ResultModel resultModel = new ResultModel();
            resultModel.setCode(4);
            return ResultModelTool.handleResultModel(resultModel);
        }
    }

}
