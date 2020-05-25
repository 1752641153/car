package com.itwanli.service.impl;

import com.itwanli.dao.CarOwnerRepository;
import com.itwanli.entity.CarOwner;
import com.itwanli.result.ErrorCode;
import com.itwanli.service.CarOwnerService;
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
public class CarOwnerServiceImpl implements CarOwnerService {

    @Autowired
    private CarOwnerRepository carOwnerRepository;

    @Transactional
    @Override
    public CarOwner getCarOwner(Long id) {
        CarOwner carOwner = null;
        if (carOwnerRepository.existsById(id)){
            carOwner = carOwnerRepository.findById(id).get();
        }
        return carOwner;
    }

    @Transactional
    @Override
    public List<CarOwner> listCarOwner() {
        return carOwnerRepository.findByValidIsTrue();
    }

    @Transactional
    @Override
    public Page<CarOwner> listCarOwner(Pageable pageable) {
/*        List<CarOwner> carOwners = carOwnerRepository.findByValidIsTrue();
        Page<CarOwner> all = new PageImpl<>(carOwners);*/
        return carOwnerRepository.findByValid(pageable);
    }

    @Transactional
    @Override
    public int saveCarOwner(CarOwner carOwner) {
        carOwner.setValid(true);
        carOwner.setPassword(MD5Utils.code(carOwner.getPassword()));
        carOwner.setEarnings(0);
        carOwner.setCreateTime(new Date());
        carOwner.setLoginTime(new Date());
        CarOwner saveCarOwner = carOwnerRepository.save(carOwner);
        if (saveCarOwner != null) {
            System.out.println("save success");
            return ErrorCode.ADDSUCCESS;
        } else {
            System.out.println("save failure");
            return ErrorCode.ADDFAIL;
        }

    }

    @Transactional
    @Override
    public int updateCarOwner(Long id, CarOwner carOwner){
        if (carOwnerRepository.existsById(id)){
            CarOwner carOwner1 = carOwnerRepository.findById(id).get();
            carOwner.setId(id);
            if (carOwner.getPassword() != null){
                carOwner.setPassword(MD5Utils.code(carOwner.getPassword()));
            }else{
                carOwner.setPassword(carOwner.getPassword());
            }
            carOwner.setEarnings(carOwner1.getEarnings());
            carOwner.setCreateTime(carOwner1.getCreateTime());
            carOwner.setLoginTime(carOwner1.getLoginTime());
            carOwner.setValid(true);
            BeanUtils.copyProperties(carOwner,carOwner1);
            carOwnerRepository.save(carOwner1);
            System.out.println("更新成功");
            return ErrorCode.UPDATESUCCESS;
        }
        System.out.println("更新失败");
        return ErrorCode.UPDATEFAIL;
    }

    @Transactional
    @Override
    public int deleteCarOwner(Long id) {
        if (carOwnerRepository.existsById(id)) {
            CarOwner carOwner = carOwnerRepository.findById(id).get();
            carOwner.setValid(false);
            System.out.println("删除成功");
            return ErrorCode.DELETESUCCESS;
        }
        System.out.println("删除失败");
        return ErrorCode.NOTEXISTUSER;
    }

    @Transactional
    @Override
    public CarOwner login(String username, String password) {
        // return userRepository.findByUsernameAndPassword(user.getUsername(),user.getPassword());
        return carOwnerRepository.findByUsernameAndPassword(username, MD5Utils.code(password));
    }
}
