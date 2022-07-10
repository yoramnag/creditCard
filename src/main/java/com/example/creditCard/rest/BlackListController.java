package com.example.creditCard.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.creditCard.entity.BlackList;
import com.example.creditCard.service.BlackListService;

@Controller
public class BlackListController {
	
	@Autowired
	private BlackListService blackListService;

	// create a mapping for "/hello"
	
	@GetMapping("/hello")
	public String sayHello(Model theModel) {
		
		theModel.addAttribute("theDate", new java.util.Date());
		
		return "helloworld";
	}
	
	@GetMapping("/black-list-cards")
	public String retrieveAllBlackListCards(Model theModel) {
		List<BlackList> blackList = blackListService.findAll();
		theModel.addAttribute("blaclListCards", blackList);
		return "black-cards-list";
		
	}
}








