package com.itwanli.service.impl;

import com.itwanli.dao.AdminRepository;
import com.itwanli.entity.Admin;
import com.itwanli.service.AdminService;
import com.itwanli.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

}
