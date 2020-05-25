package com.itwanli.service;

import com.itwanli.entity.Admin;

import java.util.List;

public interface AdminService {

    /*判断登录*/
    /*Admin checkAdmin(String username, String password);*/
    Admin checkAdmin(Admin admin);

    /*查询所有管理员*/
    List<Admin> getAllAdmin();

    /*权限管理*/
    /*permissionSetting();*/

}
