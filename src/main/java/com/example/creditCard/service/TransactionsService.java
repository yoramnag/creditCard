package com.example.creditCard.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.creditCard.dao.TransactionsRepository;
import com.example.creditCard.entity.Transactions;
import com.example.creditCard.exception.BlackListCardNotFoundException;
import com.example.creditCard.exception.FraudException;
import com.example.creditCard.exception.TransactionsNotFoundException;

@Service
public class TransactionsService {
	
	@Autowired
	private TransactionsRepository transactionsRepository;
	
	@Autowired
	private BlackListService blackListService;
	
	final int MAX_TRANSACTION_PER_A_DAY = 4;
	final double MAX_AMOUNT_PER_A_DAY = 3000;
	
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
		transactions.setDate(new Date());
		checkForFraud(transactions);
//		List<Transactions> transactionsToday = transactionsRepository.findByCreditCard(transactions.getCreditCard());
//		System.out.println(transactionsToday);
//		List<Transactions> transactionsToday2 = transactionsRepository.findByDate(transactions.getDate());
//		System.out.println(transactionsToday2);
		return transactionsRepository.save(transactions);
	}
	
	private void checkForFraud(Transactions transactions) {
		
		double sum = 0;
		if(blackListService.findBlackListCard(transactions.getCreditCard())) {
			throw new FraudException("transactions is not valid , card was found in black list !!!");
		}
		List<Transactions> transactionsToday = transactionsRepository.findByCreditCardAndDate(transactions.getCreditCard(), transactions.getDate());
		if(transactionsToday.size() > MAX_TRANSACTION_PER_A_DAY) {
			throw new FraudException("transactions is not valid , card hes passed his max transactions per a day  ");
		}
		if(transactionsToday.size() > 0) {
			for (int i = 0; i < transactionsToday.size(); i++) {
				sum = sum + transactionsToday.get(i).getAmount();
			}
			if (sum > MAX_AMOUNT_PER_A_DAY) {
				throw new FraudException("transactions is not valid , card hes passed his max amount per a day  ");
			}
		}
		
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
	
	public List<Transactions> findBycreditCardAndDate(String creditCardNumber, Date now) {
		return transactionsRepository.findByCreditCard(creditCardNumber);
	}
	
	

}
