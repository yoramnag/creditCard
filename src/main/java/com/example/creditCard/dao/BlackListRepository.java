package com.example.creditCard.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.creditCard.entity.BlackList;

@Repository
public interface BlackListRepository extends JpaRepository<BlackList, Integer>{

}
