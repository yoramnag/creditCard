package com.example.creditCard.controller;

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

	@GetMapping("/black-list-cards")
	public String retrieveAllBlackListCards(Model theModel) {
		List<BlackList> blackList = blackListService.findAll();
		theModel.addAttribute("blaclListCards", blackList);
		return "blackListHTML/black-cards-list";
		
	}
}








