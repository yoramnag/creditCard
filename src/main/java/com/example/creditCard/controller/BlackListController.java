package com.example.creditCard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.creditCard.entity.BlackList;
import com.example.creditCard.service.BlackListService;

@Controller
@RequestMapping("/blaclListHTML")
public class BlackListController {
	
	@Autowired
	private BlackListService blackListService;

	@GetMapping("/black-list-cards")
	public String listOfBlackListCards(Model theModel) {
		List<BlackList> blackList = blackListService.findAll();
		theModel.addAttribute("blaclListCards", blackList);
		return "blackListHTMLPage/black-cards-list";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		// create model attribute to bind form data
		BlackList blackList = new BlackList();
		
		theModel.addAttribute("blaclListCard", blackList);
		return "blackListHTMLPage/black-card-form";
		
	}
}








