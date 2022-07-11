package com.example.creditCard.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.creditCard.entity.Transactions;

@DataJpaTest
public class TransactionsRepositoryTest {
	
	@Autowired
	private TransactionsRepository transactionsRepository;
	
	@Test
	public void testFindAllTransactions() {
		List<Transactions> transactions = transactionsRepository.findAll();
		assertEquals(2,transactions.size());
	}
	
	@Test
	public void testFindOne() {
		Transactions transaction = transactionsRepository.findById(20001).get();
		assertEquals(100.00,transaction.getAmount());
	}

}
