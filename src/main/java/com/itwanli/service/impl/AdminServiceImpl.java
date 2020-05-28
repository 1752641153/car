package com.itwanli.service.impl;

import com.itwanli.dao.AdminRepository;
import com.itwanli.entity.Admin;
import com.itwanli.service.AdminService;
import com.itwanli.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Transactional
    @Override
    public Admin checkAdmin(Admin admin) {
        return adminRepository.findByUsernameAndPassword(admin.getUsername(), MD5Utils.code(admin.getPassword()));
    }

    @Transactional
    @Override
    public List<Admin> getAllAdmin() {
        return adminRepository.findAll();
    }

    @Override
    public Admin findByUsername(String username) {
        return adminRepository.findByUsername(username);
    }

    //12小时后失效
    private final static int EXPIRE = 12;

    @Override
    public String createToken(Admin admin) {
        //用UUID生成token
        String token = UUID.randomUUID().toString();
        //当前时间
        LocalDateTime now = LocalDateTime.now();
        //过期时间
        LocalDateTime expireTime = now.plusHours(EXPIRE);
        //保存到数据库
        admin.setLoginTime(now);
        admin.setExpireTime(expireTime);
        admin.setToken(token);
        adminRepository.save(admin);
        return token;
    }

    @Override
    public void logout(String token) {
        Admin admin = adminRepository.findByToken(token);
        //用UUID生成token
        token = UUID.randomUUID().toString();
        //修改用户的token使原本的token失效，前端需配合将token清除
        admin.setToken(token);
        adminRepository.save(admin);
    }

    @Override
    public Admin findByToken(String token) {
        return adminRepository.findByToken(token);
    }

}
