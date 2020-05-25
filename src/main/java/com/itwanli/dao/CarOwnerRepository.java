package com.itwanli.dao;

import com.itwanli.entity.CarOwner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarOwnerRepository extends JpaRepository<CarOwner,Long> {

    List<CarOwner> findByValidIsTrue();

    /*查找所有有效值为1的用户进行分页展示*/
    @Query(value = "select c from CarOwner c where c.valid = true")
    Page<CarOwner> findByValid(Pageable pageable);

    CarOwner findByUsernameAndPassword(String username, String password);

}

