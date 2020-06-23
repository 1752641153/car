package com.itwanli.dao;

import com.itwanli.entity.Indent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IndentRepository extends JpaRepository<Indent,Long> {

    List<Indent> findByValidIsTrue();

    @Query(value = "select r from Indent r where r.user.id = ?1")
    List<Indent> findUserIndent(Long uid);

    @Query(value = "select r from Indent r where r.carOwner.id = ?1")
    List<Indent> findCarOwnerIndent(Long cid);

    Indent findByNumbers(String numbers);
}
