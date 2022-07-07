package com.example.creditCard.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.creditCard.entity.Transactions;

@Repository
public interface TransactionsRepository extends JpaRepository<Transactions, Integer>{

}
