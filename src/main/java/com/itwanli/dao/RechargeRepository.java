package com.itwanli.dao;

import com.itwanli.entity.Recharge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RechargeRepository extends JpaRepository<Recharge,Long> {

    List<Recharge> findByValidIsTrue();

    @Query(value = "select r from Recharge r where r.user.id = ?1")
    List<Recharge> findUserRecharge(Long uid);
}
