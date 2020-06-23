package com.itwanli.service.impl;

import com.itwanli.dao.CarOwnerRepository;
import com.itwanli.dao.IndentRepository;
import com.itwanli.dao.UserRepository;
import com.itwanli.entity.CarOwner;
import com.itwanli.entity.Indent;
import com.itwanli.entity.User;
import com.itwanli.result.ErrorCode;
import com.itwanli.service.IndentService;
import com.itwanli.utils.NumbersUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class IndentServiceImpl implements IndentService {

    @Autowired
    private IndentRepository indentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CarOwnerRepository carOwnerRepository;

    @Transactional
    @Override
    public Indent getIndent(Long id) {
        Indent indent = null;
        if (indentRepository.existsById(id)){
            indent = indentRepository.findById(id).get();
        }
        return indent;
    }

    @Override
    public Indent getIndent(String numbers) {
        return indentRepository.findByNumbers(numbers);
    }

    @Transactional
    @Override
    public List<Indent> listIndent() {
        return indentRepository.findByValidIsTrue();
    }

    @Override
    public List<Indent> listIndent(Long uid) {
        return indentRepository.findUserIndent(uid);
    }

    @Override
    public List<Indent> listIndentByCarOwner(Long cid) {
        return indentRepository.findCarOwnerIndent(cid);
    }

    @Transactional
    @Override
    public int saveIndent(Long uid,Long cid,Indent indent) {
        User user = userRepository.findById(uid).get();
        CarOwner carOwner = carOwnerRepository.findById(cid).get();
        user.setBalance(user.getBalance()-indent.getMoney());
        carOwner.setEarnings(carOwner.getEarnings()+indent.getMoney());
        indent.setValid(true);
        indent.setNumbers(NumbersUtils.RandomCode());
        indent.setGrade("未评分");
        indent.setEvaluate("未评价");
        if (indent.getGrade() == "未评分"){
            indent.setState("进行中");
        }else {
            indent.setState("完成(已评价)");
        }
        indent.setUser(user);
        indent.setCarOwner(carOwner);
        Indent saveIndent = indentRepository.save(indent);
        if (saveIndent != null) {
            System.out.println("save success");
            return ErrorCode.ADDSUCCESS;
        } else {
            System.out.println("save failure");
            return ErrorCode.ADDFAIL;
        }
    }

    @Override
    public int saveEvaluate(Long id, Indent indent) {
        if(indentRepository.existsById(id)){
            Indent indent1 = indentRepository.findById(id).get();
            indent.setId(id);
            indent.setNumbers(indent1.getNumbers());
            indent.setDate(indent1.getDate());
            indent.setCreateTime(indent1.getCreateTime());
            indent.setOverTime(indent1.getOverTime());
            indent.setMoney(indent1.getMoney());
            if (indent.getGrade() == "未评分"){
                indent.setState("进行中");
            }else {
                indent.setState("完成(已评价)");
            }
            indent.setUser(indent1.getUser());
            indent.setCarOwner(indent1.getCarOwner());
            indent.setValid(true);
            BeanUtils.copyProperties(indent,indent1);
            indentRepository.save(indent1);
            System.out.println("评价成功");
            return ErrorCode.ADDSUCCESS;
        } else {
            System.out.println("评价失败");
            return ErrorCode.ADDFAIL;
        }
    }

    @Transactional
    @Override
    public int updateIndent(Long id, Indent indent) {
        if (indentRepository.existsById(id)){
            Indent indent1 = indentRepository.findById(id).get();
            indent.setId(id);
            indent.setValid(true);
            BeanUtils.copyProperties(indent,indent1);
            indentRepository.save(indent1);
            System.out.println("更新成功");
            return ErrorCode.UPDATESUCCESS;
        }
        System.out.println("更新失败");
        return ErrorCode.UPDATEFAIL;
    }

    @Transactional
    @Override
    public int deleteIndent(Long id) {
        if (indentRepository.existsById(id)) {
            Indent indent = indentRepository.findById(id).get();
            indent.setValid(false);
            System.out.println("删除成功");
            return ErrorCode.DELETESUCCESS;
        }
        System.out.println("删除失败");
        return ErrorCode.NOTEXISTUSER;
    }
}
