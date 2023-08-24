package com.scheduLink.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int customer_id;
	private String username;
	private String password;
	private String full_name;
	private String email;
	private String phone_number;
	
	@OneToMany(mappedBy =  "customer",cascade =CascadeType.ALL , fetch =FetchType.EAGER)
	List<Appointment> appoinment = new ArrayList<>();
	
	public Customer() {
		// TODO Auto-generated constructor stub
	}

	public Customer(String username, String password, String full_name, String email, String phone_number) {
		super();
		this.username = username;
		this.password = password;
		this.full_name = full_name;
		this.email = email;
		this.phone_number = phone_number;
		
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public List<Appointment> getAppoinment() {
		return appoinment;
	}

	public void setAppoinment(List<Appointment> appoinment) {
		this.appoinment = appoinment;
	}

	@Override
	public String toString() {
		return "Customer [customer_id=" + customer_id + ", username=" + username + ", password=" + password
				+ ", full_name=" + full_name + ", email=" + email + ", phone_number=" + phone_number + ", appoinment="
				+ appoinment + "]";
	}
	
	
	
	
	
	
}
