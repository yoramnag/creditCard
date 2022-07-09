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
	
	public BlackList saveblackListRepository (BlackList blackList) {
		if (!Utils.luhnValidetor(blackList.getCreditCard())) {
			throw new BlackListCardNotFoundException(blackList.getCreditCard() + "is not valid");
		}
		blackList.setCreditCard(Utils.maskCreditCard(blackList.getCreditCard()));
		return blackListRepository.save(blackList);
	}
	
	public boolean isBlaclListCardExist(int id) {
		Optional<BlackList> BlackListCard = blackListRepository.findById(id);
		
		if(!BlackListCard.isPresent()) {
			throw new BlackListCardNotFoundException("id - " + id);
		}
		return true;
	}
	
	public void deleteBlaclListCard(int id) {
		blackListRepository.deleteById(id);
	}
	
	public boolean findBlackListCard(String creditCardNumber){
		
		Optional<BlackList> blackList = blackListRepository.findByCreditCard(creditCardNumber);
		if(!blackList.isPresent()) {
			return false;
		}
		return true;
	}
	

}
