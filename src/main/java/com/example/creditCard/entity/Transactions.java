package com.example.creditCard.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Transactions {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="credit_card")
	private String creditCard;
	
	@Column(name="amount")
	@DecimalMin(value = "1.0", message = "amount should be greater than 1")
	private double amount ;
	
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@Column(name="mask_credit_card")
	private String maskCreditCard; 

	public Transactions() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Transactions(int id, String creditCard,
			@DecimalMin(value = "1.0", message = "amount should be greater than 1") double amount, Date date,
			String maskCreditCard) {
		super();
		this.id = id;
		this.creditCard = creditCard;
		this.amount = amount;
		this.date = date;
		this.maskCreditCard = maskCreditCard;
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

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getMaskCreditCard() {
		return maskCreditCard;
	}

	public void setMaskCreditCard(String maskCreditCard) {
		this.maskCreditCard = maskCreditCard;
	}

	@Override
	public String toString() {
		return "Transactions [id=" + id + ", creditCard=" + creditCard + ", amount=" + amount + ", date=" + date
				+ ", maskCreditCard=" + maskCreditCard + "]";
	}
	
}
