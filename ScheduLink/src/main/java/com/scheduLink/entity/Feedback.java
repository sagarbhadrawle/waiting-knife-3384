package com.scheduLink.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Feedback {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int feedback_id;
	
	@OneToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	@OneToOne
	@JoinColumn(name="service_providerId")
	private ServiceProvider service ;
	
	
	
	
	private int rating;
	private String comment;
	
	

	
	public Feedback(Customer customer_id, ServiceProvider service, int rating, String comment) {
		super();
		this.customer = customer_id;
		this.service = service;
		this.rating = rating;
		this.comment = comment;
	}


	public Feedback() {
		// TODO Auto-generated constructor stub
	}


	public int getFeedback_id() {
		return feedback_id;
	}


	public void setFeedback_id(int feedback_id) {
		this.feedback_id = feedback_id;
	}


	public Customer getCustomer_id() {
		return customer;
	}


	public void setCustomer_id(Customer customer_id) {
		this.customer = customer_id;
	}


	public int getRating() {
		return rating;
	}


	public void setRating(int rating) {
		this.rating = rating;
	}


	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}


	@Override
	public String toString() {
		return "Feedback [feedback_id=" + feedback_id + "rating=" + rating
				+ ", comment=" + comment + "]";
	}
	
	

}
