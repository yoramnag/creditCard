package com.example.creditCard.service;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.creditCard.dao.TransactionsRepository;
import com.example.creditCard.entity.Transactions;

@ExtendWith(MockitoExtension.class)
public class TransactionsServiceTest {
	
	@InjectMocks
	private TransactionsService transactionsService;
	
	@Mock
	private TransactionsRepository transactionsRepository;
	
	@Test
	public void retrieveAllTransactions_basic() {
		when(transactionsRepository.findAll()).thenReturn(Arrays.asList(new Transactions(1, "89474490a51a4906a89d5ffc873bfe97d5e695a75c8a8b1ae04b4dc44915f22e", 700, null, null),
				new Transactions(2, "89474490a51a4906a89d5ffc873bfe97d5e695a75c8a8b1ae04b4dc44915f22e", 500, null, null)));
		List<Transactions> transactions = transactionsRepository.findAll();
		
		assertEquals(700,transactions.get(0).getAmount());
	}
	
	@Test
	public void retrieveOneTransactions_basic() {
		Optional<Transactions> transactionsDemo = Optional.ofNullable(new Transactions(1, "89474490a51a4906a89d5ffc873bfe97d5e695a75c8a8b1ae04b4dc44915f22e", 700, null, null));
		when(transactionsRepository.findById(1)).thenReturn(transactionsDemo);
		
		Optional<Transactions> transaction = transactionsRepository.findById(1);
		assertEquals("89474490a51a4906a89d5ffc873bfe97d5e695a75c8a8b1ae04b4dc44915f22e",transaction.get().getCreditCard());
		
		
	}
	

}
