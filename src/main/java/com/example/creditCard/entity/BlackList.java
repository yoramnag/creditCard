package com.example.creditCard.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
public class BlackList {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="credit_card")
	@Size(min=16, max = 16 ,message="credit card size should be 16")
	private String creditCard;

	public BlackList() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BlackList(int id, @Size(min = 12, max = 12, message = "credit card size should be 12") String creditCard) {
		super();
		this.id = id;
		this.creditCard = creditCard;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(String creditCard) {
		this.creditCard = creditCard;
	}

	@Override
	public String toString() {
		return "BlackList [id=" + id + ", creditCard=" + creditCard + "]";
	}

	
	
	

}
