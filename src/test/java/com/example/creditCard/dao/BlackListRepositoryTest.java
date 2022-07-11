package com.example.creditCard.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.creditCard.entity.BlackList;

@DataJpaTest
public class BlackListRepositoryTest {
	
	@Autowired
	private BlackListRepository blackListRepository;
	
	@Test
	public void testFindAllBlackList() {
		List<BlackList> BlackList = blackListRepository.findAll();
		assertEquals(3,BlackList.size());
	}
	
	@Test
	public void testFindOne() {
		BlackList blackList = blackListRepository.findById(10001).get();
		assertEquals("56e7fc920e4283f65412b1668110f5bf9552a697b90928869219158d87b70be7",blackList.getCreditCard());
	}

}
