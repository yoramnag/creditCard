package com.example.creditCard.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.creditCard.entity.Transactions;
import com.example.creditCard.service.TransactionsService;

@RestController
@RequestMapping("/api")
public class TransactionsRestController {
	
	@Autowired
	private TransactionsService transactionsService;
	
	@GetMapping("/transactions")
	public List<Transactions> retrieveAllTransactions(){
		return transactionsService.findAll();
	}

}
