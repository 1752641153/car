package com.itwanli.service.impl;

import com.itwanli.dao.UserRepository;
import com.itwanli.entity.User;
import com.itwanli.result.ErrorCode;
import com.itwanli.service.UserService;
import com.itwanli.utils.MD5Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Override
    public User getUser(Long id) {
        User user = null;
        if (userRepository.existsById(id)){
            user = userRepository.findById(id).get();
        }
        return user;
    }

    @Transactional
    @Override
    public List<User> listUser() {
        return userRepository.findByValidIsTrue();
    }

    @Transactional
    @Override
    public Page<User> listUser(Pageable pageable) {
/*        List<User> users = userRepository.findByValidIsTrue();
        Page<User> all = new PageImpl<>(users);*/
        return userRepository.findByValidIsTrue(pageable);
    }

    @Transactional
    @Override
    public int saveUser(User user) {
        user.setValid(true);
        user.setPassword(MD5Utils.code(user.getPassword()));
        user.setBalance(0);
        user.setCreateTime(new Date());
        user.setLoginTime(new Date());
        User saveUser = userRepository.save(user);
        if (saveUser != null) {
            System.out.println("save success");
            return ErrorCode.ADDSUCCESS;
        } else {
            System.out.println("save failure");
            return ErrorCode.ADDFAIL;
        }

    }

    @Transactional
    @Override
    public int updateUser(Long id, User user){
        if (userRepository.existsById(id)){
            User user1 = userRepository.findById(id).get();
            user.setId(id);
            if (user.getPassword() != null){
                user.setPassword(MD5Utils.code(user.getPassword()));
            }else{
                user.setPassword(user1.getPassword());
            }
            user.setBalance(user1.getBalance());
            user.setCreateTime(user1.getCreateTime());
            user.setLoginTime(user1.getLoginTime());
            user.setValid(true);
            BeanUtils.copyProperties(user,user1);
            userRepository.save(user1);
            System.out.println("更新成功");
            return ErrorCode.UPDATESUCCESS;
        }
        System.out.println("更新失败");
        return ErrorCode.UPDATEFAIL;
    }

    @Transactional
    @Override
    public int deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            User user = userRepository.findById(id).get();
            user.setValid(false);
            userRepository.save(user);
            System.out.println("删除成功");
            return ErrorCode.DELETESUCCESS;
        }
        System.out.println("删除失败");
        return ErrorCode.NOTEXISTUSER;
    }

    @Transactional
    @Override
    public User login(String username, String password) {
       // return userRepository.findByUsernameAndPassword(user.getUsername(),user.getPassword());
        return userRepository.findByUsernameAndPassword(username, MD5Utils.code(password));
    }
}
