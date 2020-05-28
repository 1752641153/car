package com.itwanli.dao;

import com.itwanli.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,Long> {

    Admin findByUsernameAndPassword(String username,String password);

    Admin findByUsername(String username);

    Admin findByToken(String token);

}
