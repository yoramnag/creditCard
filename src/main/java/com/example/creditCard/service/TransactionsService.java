package com.example.creditCard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.creditCard.dao.TransactionsRepository;
import com.example.creditCard.entity.Transactions;

@Service
public class TransactionsService {
	
	@Autowired
	private TransactionsRepository transactionsRepository;
	
	public List<Transactions> findAll(){
		return transactionsRepository.findAll();
	}

}
