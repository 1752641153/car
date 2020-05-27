package com.itwanli.controller;

import com.itwanli.entity.User;
import com.itwanli.result.ResultModel;
import com.itwanli.result.ResultModelTool;
import com.itwanli.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin  //允许跨域访问
@RestController
@RequestMapping("/park")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/users",method = RequestMethod.GET)
    public ResultModel getAllUser(){
        List<User> userList = userService.listUser();
        Map<String,List<User>> UserMap = new HashMap<>();
        if (userList!=null){
            UserMap.put("users",userList);
        }
        ResultModel resultModel = new ResultModel();
        resultModel.setCode(0);
        resultModel.setData(UserMap);
        return ResultModelTool.handleResultModel(resultModel);
    }

    @GetMapping("/users/{id}")
    public ResultModel getUser(@PathVariable Long id){
        User user = userService.getUser(id);
        ResultModel resultModel = new ResultModel();
        resultModel.setCode(0);
        resultModel.setData(user);
        return ResultModelTool.handleResultModel(resultModel);
    }

    @GetMapping("/users/{page}/{size}")
    public ResultModel listUser(@PathVariable Integer page,@PathVariable Integer size){
        Pageable pageable = PageRequest.of(page-1,size);
        Page<User> user = userService.listUser(pageable);
        ResultModel resultModel = new ResultModel();
        resultModel.setCode(0);
        resultModel.setData(user);
        return ResultModelTool.handleResultModel(resultModel);
    }

    @PostMapping("/users")
    public ResultModel addUser(@RequestBody User user){
        int errorCode = userService.saveUser(user);
        ResultModel resultModel = new ResultModel();
        resultModel.setCode(errorCode);
        resultModel.setData(user);
        return ResultModelTool.handleResultModel(resultModel);
    }


    @PutMapping("/users/{id}")
    public ResultModel updateUser(@PathVariable Long id, @RequestBody User user){
        int errorCode = userService.updateUser(id,user);
        ResultModel resultModel = new ResultModel();
        resultModel.setCode(errorCode);
        resultModel.setData(user);
        return ResultModelTool.handleResultModel(resultModel);
    }

    @DeleteMapping("/users/{id}")
    public ResultModel deleteUser(@PathVariable long id){
        int errorCode = userService.deleteUser(id);
        ResultModel resultModel = new ResultModel();
        resultModel.setCode(errorCode);
        List<User> userList = userService.listUser();
        Map<String,List<User>> UserMap = new HashMap<>();
        if (userList!=null){
            UserMap.put("users",userList);
        }
        resultModel.setData(UserMap);
        return ResultModelTool.handleResultModel(resultModel);
    }


}
