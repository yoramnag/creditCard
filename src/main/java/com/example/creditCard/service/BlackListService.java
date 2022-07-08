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
	
	public Optional<BlackList> findById(int id) {
		Optional<BlackList> blackList = blackListRepository.findById(id);
		
		if(!blackList.isPresent()) {
			throw new BlackListCardNotFoundException("id - " + id);
		}
		return blackList;
	}
	
	

}
