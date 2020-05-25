package com.itwanli.service;

import com.itwanli.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    /*根据id查询用户*/
    User getUser(Long id);

    /*查询所有用户*/
    List<User> listUser();

    /*分页查询*/
    Page<User> listUser(Pageable pageable);

    /*保存用户*/
    int saveUser(User user);

    /*修改用户信息*/
    int updateUser(Long id,User user);

    /*删除用户*/
    int deleteUser(Long id);

    /*判断登录*/
    //User login(User user);
    User login(String username,String password);

}
