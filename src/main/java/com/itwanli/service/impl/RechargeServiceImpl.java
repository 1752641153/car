package com.itwanli.service.impl;

import com.itwanli.dao.RechargeRepository;
import com.itwanli.dao.UserRepository;
import com.itwanli.entity.Recharge;
import com.itwanli.entity.User;
import com.itwanli.result.ErrorCode;
import com.itwanli.service.RechargeService;
import com.itwanli.utils.NumbersUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class RechargeServiceImpl implements RechargeService {

    @Autowired
    private RechargeRepository rechargeRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Override
    public Recharge getRecharge(Long id) {
        Recharge recharge = null;
        if (rechargeRepository.existsById(id)){
            recharge = rechargeRepository.findById(id).get();
        }
        return recharge;
    }

    @Override
    public List<Recharge> listUserRecharge(Long uid) {
        List<Recharge> list = listRecharge();
        return rechargeRepository.findUserRecharge(uid);
    }

    @Transactional
    @Override
    public List<Recharge> listRecharge() {
        return rechargeRepository.findByValidIsTrue();
    }

    @Override
    public Page<Recharge> pageRecharge(Pageable pageable) {
        return rechargeRepository.findByValidIsTrue(pageable);
    }

    @Transactional
    @Override
    public int saveRecharge(Long uid,Recharge recharge) {
        User user = userRepository.findById(uid).get();
        recharge.setValid(true);
        user.setBalance(user.getBalance()+recharge.getMoney());
        recharge.setUser(user);
        recharge.setNumbers(NumbersUtils.RandomCode());
        recharge.setCreateTime(new Date());
        recharge.setState("充值成功");
        Recharge saveRecharge = rechargeRepository.save(recharge);
        if (saveRecharge != null) {
            System.out.println("save success");
            return ErrorCode.ADDSUCCESS;
        } else {
            System.out.println("save failure");
            return ErrorCode.ADDFAIL;
        }
    }

    @Transactional
    @Override
    public int updateRecharge(Long id, Recharge recharge) {
        if (rechargeRepository.existsById(id)){
            Recharge recharge1 = rechargeRepository.findById(id).get();
            recharge.setId(id);
            recharge.setCreateTime(recharge1.getCreateTime());
            recharge.setNumbers(recharge1.getNumbers());
            recharge.setUser(recharge1.getUser());
            recharge.setValid(true);
            BeanUtils.copyProperties(recharge,recharge1);
            rechargeRepository.save(recharge1);
            System.out.println("更新成功");
            return ErrorCode.UPDATESUCCESS;
        }
        System.out.println("更新失败");
        return ErrorCode.UPDATEFAIL;
    }

    @Transactional
    @Override
    public int deleteRecharge(Long id) {
        if (rechargeRepository.existsById(id)) {
            Recharge recharge = rechargeRepository.findById(id).get();
            recharge.setValid(false);
            rechargeRepository.save(recharge);
            System.out.println("删除成功");
            return ErrorCode.DELETESUCCESS;
        }
        System.out.println("删除失败");
        return ErrorCode.NOTEXISTUSER;
    }
}
