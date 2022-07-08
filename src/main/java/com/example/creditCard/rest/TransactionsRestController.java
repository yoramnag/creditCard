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

import com.example.creditCard.entity.Transactions;
import com.example.creditCard.service.TransactionsService;

@RestController
@RequestMapping("/api")
public class TransactionsRestController {
	
	@Autowired
	private TransactionsService transactionsService;
	
	@GetMapping("/transactions")
	public List<Transactions> retrieveAllTransactions(){
		return transactionsService.findAll();
	}
	
	@GetMapping("/transactions/{id}")
	public EntityModel<Transactions> retrieveTransaction(@PathVariable int id){
		Optional<Transactions> manager = transactionsService.findById(id);
		EntityModel<Transactions> resource = EntityModel.of(manager.get());
		WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllTransactions());
		resource.add(linkTo.withRel("all-managers"));
		return resource;
	}

}
