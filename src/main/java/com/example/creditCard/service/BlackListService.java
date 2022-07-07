package com.example.creditCard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.creditCard.dao.BlackListRepository;
import com.example.creditCard.entity.BlackList;

@Service
public class BlackListService {
	
	@Autowired
	private BlackListRepository blackListRepository;
	
	public List<BlackList> findAll(){
		
		return blackListRepository.findAll();
		
	}

}
