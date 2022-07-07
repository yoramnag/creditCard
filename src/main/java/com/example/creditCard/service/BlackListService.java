package com.example.creditCard.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.creditCard.dao.BlackListRepository;
import com.example.creditCard.entity.BlackList;
import com.example.creditCard.exception.BlackListCardNotFoundException;

@Service
public class BlackListService {
	
	@Autowired
	private BlackListRepository blackListRepository;
	
	public List<BlackList> findAll(){
		return blackListRepository.findAll();
	}
	
	public Optional<BlackList> findById(int cardNumber) {
		Optional<BlackList> manager = blackListRepository.findById(cardNumber);
		
		if(!manager.isPresent()) {
			throw new BlackListCardNotFoundException("card number" + cardNumber + "was not found in bkack list");
		}
		return manager;
	}
	
	

}
