package com.example.creditCard.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.creditCard.dao.TransactionsRepository;
import com.example.creditCard.entity.BlackList;
import com.example.creditCard.entity.Transactions;
import com.example.creditCard.exception.FraudException;
import com.example.creditCard.exception.LuhnException;
import com.example.creditCard.exception.TransactionsNotFoundException;

@Service
public class TransactionsService {
	
	@Autowired
	private TransactionsRepository transactionsRepository;
	
	@Autowired
	private BlackListService blackListService;
	
	@Value("${spring.datasource.maxTrsnsactionsPerADAy}")
	int MAX_TRANSACTION_PER_A_DAY;
	@Value("${spring.datasource.maxAmountPerADay}")
	double MAX_AMOUNT_PER_A_DAY;
	
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
		checkLuhnValidetor(transactions);
		transactions.setDate(new Date());
		checkForFraud(transactions);
		transactions.setMaskCreditCard(Utils.mask(transactions.getCreditCard()));
		transactions.setCreditCard(Utils.maskCreditCard(transactions.getCreditCard()));
		return transactionsRepository.save(transactions);
	}
	
	// Check if the transaction is valid and it's not a fraud
	private void checkForFraud(Transactions transactions) {
		checkBlackList(transactions);
		List<Transactions> transactionsToday = findByCreditCardAndDate(transactions);
		checkTransactionsPerADay(transactionsToday,transactions);
		checkAmountPerADay(transactions, transactionsToday);
	}
	
	// Add card to the blacklist 
	private void addCardToBlalList(Transactions transactions) {
		BlackList blackList = new BlackList();
		blackList.setCreditCard(transactions.getCreditCard());
		blackListService.saveBlackListRepository(blackList);
		
	}
	
	// Check if the user pass the max amount per a day  
	private void checkAmountPerADay(Transactions transactions ,List<Transactions> transactionsToday ) {
		double sum = 0;
		if(transactions.getAmount() >= MAX_AMOUNT_PER_A_DAY) {
			addCardToBlalList(transactions);
			throw new FraudException("transactions is not valid , card passed his max amount per a day  ");
		}
		if(transactionsToday.size() > 0) {
			for (int i = 0; i < transactionsToday.size(); i++) {
				sum = sum + transactionsToday.get(i).getAmount();
			}
			if (sum >= MAX_AMOUNT_PER_A_DAY) {
				addCardToBlalList(transactions);
				throw new FraudException("transactions is not valid , card passed his max amount per a day  ");
			}
		}
	}
	
	// Check if the user pass the max transaction per a day  
	private void checkTransactionsPerADay(List<Transactions> transactionsToday, Transactions transactions) {
		if(transactionsToday.size()+1 > MAX_TRANSACTION_PER_A_DAY) {
			addCardToBlalList(transactions);
			throw new FraudException("transactions is not valid , card passed his max transactions per a day  ");
		}
	}
	
	// Check if credit card i on blacklist
	private void checkBlackList(Transactions transactions) {
		if(blackListService.findBlackListCard(transactions.getCreditCard())) {
			throw new FraudException("transactions is not valid , card was found in black list !!!");
		}
	}
	
	// Validate credit card with luhn algorithm
	private void checkLuhnValidetor(Transactions transactions) {
		if (!Utils.luhnValidetor(transactions.getCreditCard())) {
			throw new LuhnException(Utils.mask(transactions.getCreditCard()) + " is not valid");
		}
	}
	

	// Delete Transaction
	public void deleteTransaction(int id) {
		transactionsRepository.deleteById(id);
	}
	
	// Check if transactions exist in the database
	public boolean isTransactionExist(int id) {
		Optional<Transactions> transactions = transactionsRepository.findById(id);
		
		if(!transactions.isPresent()) {
			throw new TransactionsNotFoundException("id - " + id);
		}
		return true;
	}
	
	// Get the user transactions for a given date
	public List<Transactions> findByCreditCardAndDate(Transactions transactions){
		
		String creditCardNumber = Utils.maskCreditCard(transactions.getCreditCard());
		List<Transactions> transactionsToday = transactionsRepository.findByCreditCardAndDate(creditCardNumber, transactions.getDate());
		if(!transactionsToday.isEmpty()) {
			return transactionsToday;
		}
		return transactionsToday;
	}
	
	
	

}
