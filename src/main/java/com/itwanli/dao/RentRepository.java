package com.itwanli.dao;

import com.itwanli.entity.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RentRepository extends JpaRepository<Rent,Long> {

    List<Rent> findByValidIsTrue();

    @Query(value = "select r from Rent r where r.carOwner.id = ?1")
    List<Rent> findCarOwnerRent(Long cid);
}
