package com.example.creditCard.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.creditCard.entity.Transactions;

@Repository
public interface TransactionsRepository extends JpaRepository<Transactions, Integer>{
	
	List<Transactions> findByCreditCard(String creditCardNumber);
	
	List<Transactions> findByDate(Date date);
	
	List<Transactions> findByCreditCardAndDate(String creditCardNumber,Date date);

}
