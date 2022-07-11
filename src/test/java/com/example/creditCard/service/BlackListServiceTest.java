package com.example.creditCard.service;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.creditCard.dao.BlackListRepository;
import com.example.creditCard.entity.BlackList;

@ExtendWith(MockitoExtension.class)
public class BlackListServiceTest {
	
	@InjectMocks
	private BlackListService blackListService;
	
	@Mock
	private BlackListRepository blackListRepository;
	
	@Test
	public void retrieveAllBlackLists_basic() {
		when(blackListRepository.findAll()).thenReturn(Arrays.asList(new BlackList(10001,"56e7fc920e4283f65412b1668110f5bf9552a697b90928869219158d87b70be7","XXXXXXXXXXXX6705"),
				new BlackList(10002,"147a30c6e914bffaaaa957d9287226759a27d3eb5d27212c901e5944d565a7dc","XXXXXXXXXXXX5870")));
		List<BlackList> blackLists = blackListService.findAll();
		
		assertEquals("XXXXXXXXXXXX6705",blackLists.get(0).getMaskCreditCard());
	}
	
	@Test
	public void retrieveOneManager_basic() {
		Optional<BlackList> blackListDemo = Optional.ofNullable(new BlackList(10002,"147a30c6e914bffaaaa957d9287226759a27d3eb5d27212c901e5944d565a7dc","XXXXXXXXXXXX5870"));
		when(blackListRepository.findById(1)).thenReturn(blackListDemo);
		
		Optional<BlackList> blackList = blackListService.findById(1);
		assertEquals("147a30c6e914bffaaaa957d9287226759a27d3eb5d27212c901e5944d565a7dc",blackList.get().getCreditCard());
	}
	
	

}
