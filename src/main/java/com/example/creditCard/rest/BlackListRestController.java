package com.example.creditCard.rest;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	
	@PostMapping("/blacklist")
	public ResponseEntity<Object> createBlackListCard(@Valid @RequestBody BlackList blackList) {
		blackList.setId(0);
		BlackList savedBlackList = blackListService.saveBlackListRepository(blackList);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedBlackList.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}
	
	@PutMapping("/blacklist")
	public ResponseEntity<Object> updateBlackListCard(@Valid @RequestBody BlackList blackListCard) {
		if(blackListService.isBlaclListCardExist(blackListCard.getId())) {
			blackListService.saveBlackListRepository(blackListCard);
		}
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/blacklist/{id}")
	public ResponseEntity<Object> deleteBlackListCard(@PathVariable int id) {
		if(blackListService.isBlaclListCardExist(id)) {
			blackListService.deleteBlaclListCard(id);
		}
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/blacklistCard")
	public boolean findCreditCard(@Valid @RequestBody BlackList blackListCard) {
		return blackListService.findBlackListCard(blackListCard.getCreditCard());
	}
	

}
