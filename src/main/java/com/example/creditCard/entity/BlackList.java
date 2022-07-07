package com.example.creditCard.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
public class BlackList {
	
	@Id
	@Column(name="credit_card")
	@Size(min=16, max = 16 ,message="credit card size should be 16")
	private String creditCard;

	public BlackList() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BlackList(@Size(min = 16, max = 16, message = "credit card size should be 16") String creditCard) {
		super();
		this.creditCard = creditCard;
	}

	public String getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(String creditCard) {
		this.creditCard = creditCard;
	}
	
	

}
