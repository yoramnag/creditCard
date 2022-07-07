package com.example.creditCard.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.creditCard.entity.BlackList;
import com.example.creditCard.service.BlackListService;

@RestController
@RequestMapping("/api")
public class BlackListRestController {
	
	@Autowired
	private BlackListService blackListService;
	
	@GetMapping("/blacklist")
	public List<BlackList> retrieveAllBlackListCards(){
		return blackListService.findAll();
	}

}
