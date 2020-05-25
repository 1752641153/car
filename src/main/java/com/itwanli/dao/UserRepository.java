package com.itwanli.dao;

import com.itwanli.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {

    List<User> findByValidIsTrue();

    /*查找所有有效值为1的用户进行分页展示*/
    @Query(value = "select u from User u where u.valid = true")/*select u from User u where u.valid = true*//*,nativeQuery = true运行原生语句*/
    Page<User> findByValid(Pageable pageable);

    User findByUsernameAndPassword(String username, String password);
}
