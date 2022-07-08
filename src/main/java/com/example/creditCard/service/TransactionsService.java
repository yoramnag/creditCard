package com.example.creditCard.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.creditCard.dao.TransactionsRepository;
import com.example.creditCard.entity.Transactions;
import com.example.creditCard.exception.TransactionsNotFoundException;

@Service
public class TransactionsService {
	
	@Autowired
	private TransactionsRepository transactionsRepository;
	
	public List<Transactions> findAll(){
		return transactionsRepository.findAll();
	}
	
	public Optional<Transactions> findById(int id) {
		Optional<Transactions> transactions = transactionsRepository.findById(id);
		
		if(!transactions.isPresent()) {
			throw new TransactionsNotFoundException("id - " + id);
		}
		return transactions;
	}

}
