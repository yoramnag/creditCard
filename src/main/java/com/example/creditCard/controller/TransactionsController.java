package com.example.creditCard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.creditCard.entity.Transactions;
import com.example.creditCard.service.TransactionsService;

@Controller
@RequestMapping("/transactionsHTML")
public class TransactionsController {
	
	@Autowired
	private TransactionsService transactionsService;
	
	@GetMapping("/transactions-list")
	public String listOfBlackListCards(Model theModel) {
		List<Transactions> transactionsList = transactionsService.findAll();
		theModel.addAttribute("transactions", transactionsList);
		return "transactionsHTMLPage/transactions-list";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		Transactions transaction = new Transactions();
		theModel.addAttribute("newTransactions", transaction);
		return "transactionsHTMLPage/transaction-form";
	}
	
	@PostMapping("/save")
	public String saveBlackListCard(@ModelAttribute("newTransactions") Transactions theTransaction) {
		// save the employee
		transactionsService.save(theTransaction);
		
		return "redirect:/transactionsHTML/transactions-list";
	}

}
