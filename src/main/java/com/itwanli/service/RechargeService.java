package com.itwanli.service;

import com.itwanli.entity.Recharge;

import java.util.List;

public interface RechargeService {

    /*根据id查询充值记录*/
    Recharge getRecharge(Long id);

    /*根据用户id查询充值记录*/
    List<Recharge> listUserRecharge(Long uid);

    /*查询所有用户的充值记录*/
    List<Recharge> listRecharge();

    /*保存充值*/
    int saveRecharge(Long uid,Recharge recharge);

    /*修改用户充值信息*/
    int updateRecharge(Long id,Recharge recharge);

    /*删除用户*/
    int deleteRecharge(Long id);

}
