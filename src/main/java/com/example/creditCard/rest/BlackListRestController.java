package com.example.creditCard.rest;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@GetMapping("/blacklist/{id}")
	public EntityModel<BlackList> retrieveBlackListCard(@PathVariable int id) {
		Optional<BlackList> blackList = blackListService.findById(id);
		EntityModel<BlackList> resource = EntityModel.of(blackList.get());
		WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllBlackListCards());
		resource.add(linkTo.withRel("all-managers"));
		return resource;
	}
	
	

}
