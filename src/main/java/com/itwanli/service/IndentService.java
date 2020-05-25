package com.itwanli.service;

import com.itwanli.entity.Indent;

import java.util.List;

public interface IndentService {

    /*根据id查询订单记录*/
    Indent getIndent(Long id);

    /*查询所有订单记录*/
    List<Indent> listIndent();

    /*查询一个用户的所有订单记录*/
    List<Indent> listIndent(Long uid);

    /*查询一个车主的所有订单记录*/
    List<Indent> listIndentByCarOwner(Long cid);

    /*下订单*/
    int saveIndent(Long uid,Long cid,Indent indent);

    /*订单评价*/
    int saveEvaluate(Long id,Indent indent);

    /*修改用户订单信息*/
    int updateIndent(Long id, Indent indent);

    /*删除订单信息*/
    int deleteIndent(Long id);
}
