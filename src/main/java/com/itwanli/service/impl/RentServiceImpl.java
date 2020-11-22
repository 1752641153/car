package com.itwanli.service.impl;

import com.itwanli.dao.CarOwnerRepository;
import com.itwanli.dao.RentRepository;
import com.itwanli.entity.Rent;
import com.itwanli.result.ErrorCode;
import com.itwanli.service.RentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RentServiceImpl implements RentService {

    @Autowired
    private RentRepository rentRepository;

    @Autowired
    private CarOwnerRepository carOwnerRepository;

    @Transactional
    @Override
    public Rent getRent(Long id) {
        Rent rent = null;
        if (rentRepository.existsById(id)){
            rent = rentRepository.findById(id).get();
        }
        return rent;
    }

    @Override
    public List<Rent> listRent(Long cid) {
        return rentRepository.findCarOwnerRent(cid);
    }

    @Transactional
    @Override
    public List<Rent> listRent() {
        return rentRepository.findByValidIsTrue();
    }

    @Override
    public Page<Rent> pageRent(Pageable pageable) {
        return rentRepository.findByValidIsTrue(pageable);
    }

    @Transactional
    @Override
    public int saveRent(Long cid, Rent rent) {
        rent.setValid(true);
        rent.setCarOwner(carOwnerRepository.findById(cid).get());
        Rent saveRent = rentRepository.save(rent);
        if (saveRent != null) {
            System.out.println("save success");
            return ErrorCode.ADDSUCCESS;
        } else {
            System.out.println("save failure");
            return ErrorCode.ADDFAIL;
        }
    }

    @Transactional
    @Override
    public int updateRent(Long id, Rent rent) {
        if (rentRepository.existsById(id)){
            Rent rent1 = rentRepository.findById(id).get();
            rent.setId(id);
            rent.setCarOwner(rent1.getCarOwner());
            rent.setValid(true);
            BeanUtils.copyProperties(rent,rent1);
            rentRepository.save(rent1);
            System.out.println("更新成功");
            return ErrorCode.UPDATESUCCESS;
        }
        System.out.println("更新失败");
        return ErrorCode.UPDATEFAIL;
    }

    @Transactional
    @Override
    public int deleteRent(Long id) {
        if (rentRepository.existsById(id)) {
            Rent rent = rentRepository.findById(id).get();
            rent.setValid(false);
            rentRepository.save(rent);
            System.out.println("删除成功");
            return ErrorCode.DELETESUCCESS;
        }
        System.out.println("删除失败");
        return ErrorCode.NOTEXISTUSER;
    }
}
