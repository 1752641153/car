package com.itwanli.service;

import com.itwanli.entity.Rent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RentService {

    /*根据id查询租赁记录*/
    Rent getRent(Long id);

    /*查询一个车主的租赁记录*/
    List<Rent> listRent(Long cid);

    /*查询所有用户的租赁记录*/
    List<Rent> listRent();

    /*分页查询*/
    Page<Rent> pageRent(Pageable pageable);

    /*保存租赁时间*/
    int saveRent(Long cid,Rent rent);

    /*修改用户租赁信息*/
    int updateRent(Long id, Rent rent);

    /*删除租赁信息*/
    int deleteRent(Long id);
}
