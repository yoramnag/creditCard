package com.example.creditCard.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.creditCard.dao.TransactionsRepository;
import com.example.creditCard.entity.Transactions;
import com.example.creditCard.exception.BlackListCardNotFoundException;
import com.example.creditCard.exception.TransactionsNotFoundException;

@Service
public class TransactionsService {
	
	@Autowired
	private TransactionsRepository transactionsRepository;
	
	// Return all Transactions
	public List<Transactions> findAll(){
		return transactionsRepository.findAll();
	}
	
	//Return Transaction
	public Optional<Transactions> findById(int id) {
		Optional<Transactions> transactions = transactionsRepository.findById(id);
		
		if(!transactions.isPresent()) {
			throw new TransactionsNotFoundException("id - " + id);
		}
		return transactions;
	}
	
	// Save new Transaction
	public Transactions save (Transactions transactions) {
		if (!Utils.luhnValidetor(transactions.getCreditCard())) {
			throw new BlackListCardNotFoundException(transactions.getCreditCard() + "is not valid");
		}
		return transactionsRepository.save(transactions);
	}
	
	// Delete Transaction
	public void deleteTransaction(int id) {
		transactionsRepository.deleteById(id);
	}
	
	public boolean isTransactionExist(int id) {
		Optional<Transactions> transactions = transactionsRepository.findById(id);
		
		if(!transactions.isPresent()) {
			throw new TransactionsNotFoundException("id - " + id);
		}
		return true;
	}
	
	

}
