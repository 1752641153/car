package com.itwanli.service;

import com.itwanli.entity.CarOwner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CarOwnerService {

    /*根据id查询用户*/
    CarOwner getCarOwner(Long id);

    /*查询所有用户*/
    List<CarOwner> listCarOwner();

    /*分页查询*/
    Page<CarOwner> listCarOwner(Pageable pageable);

    /*保存用户*/
    int saveCarOwner(CarOwner carOwner);

    /*修改用户信息*/
    int updateCarOwner(Long id, CarOwner carOwner);

    /*删除用户*/
    int deleteCarOwner(Long id);

    /*判断登录*/
    //CarOwner login(CarOwner carOwner);
    CarOwner login(String username, String password);

}
