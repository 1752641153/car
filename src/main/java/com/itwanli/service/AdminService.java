package com.itwanli.service;

import com.itwanli.entity.Admin;

import java.util.List;

public interface AdminService {

    /*判断登录*/
    /*Admin checkAdmin(String username, String password);*/
    Admin checkAdmin(Admin admin);

    /*查询所有管理员*/
    List<Admin> getAllAdmin();

    /* 根据用户名查找用户*/
    Admin findByUsername(String username);

    /*为admin生成token*/
    String createToken(Admin admin);

    /*根据token去修改管理员token ，使原本token失效*/
    void logout(String token);

    /*根据token获取用户信息*/
    Admin findByToken(String token);

    /*权限管理*/
    /*permissionSetting();*/

}
