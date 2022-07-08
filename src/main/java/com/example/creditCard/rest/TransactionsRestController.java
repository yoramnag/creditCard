package com.example.creditCard.rest;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.creditCard.entity.Transactions;
import com.example.creditCard.service.TransactionsService;

@RestController
@RequestMapping("/api")
public class TransactionsRestController {
	
	@Autowired
	private TransactionsService transactionsService;
	private static final SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy-HH.mm.ss");
	
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
	
	// expose "/manager" and save new manager
	@PostMapping("/transactions")
	public ResponseEntity<Object> createTransaction(@Valid @RequestBody Transactions transactions) {
		transactions.setId(0);
		Transactions savedTransactions = transactionsService.save(transactions);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedTransactions.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}
	
	// expose "/transactions" and update transaction info
	@PutMapping("/transactions")
	public ResponseEntity<Object> updateTransaction(@Valid @RequestBody Transactions transaction) {
		if(transactionsService.isTransactionExist(transaction.getId())) {
			transactionsService.save(transaction);
		}
		return ResponseEntity.ok().build();
	}
	
	// expose "/transactions/{id}" and delete transaction by his id
	@DeleteMapping("/transactions/{id}")
	public ResponseEntity<Object> deleteTransaction(@PathVariable int id) {
		if(transactionsService.isTransactionExist(id)) {
			transactionsService.deleteTransaction(id);
		}
		return ResponseEntity.ok().build();
	}
	
	
	
	

}
