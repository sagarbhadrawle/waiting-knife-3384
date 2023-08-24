package com.scheduLink.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class ServiceProvider {
	@Id
	private String username;
	private String name;
	private String email;
	private String password;

	@OneToMany(mappedBy = "sp",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private Set<Service> services  = new HashSet<>();
	
	@OneToMany(mappedBy = "serviceProvider",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	List<Appointment> appoinments= new ArrayList<>();

	public ServiceProvider() {
		super();
	}

	public ServiceProvider(String name, String email, String username, String password) {
		super();
		this.name = name;
		this.email = email;
		this.username = username;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public Set<Service> getServices() {
		return services;
	}

	public void setServices(Set<Service> services) {
		this.services = services;
	}
	
	public List<Appointment> getAppoinments() {
		return appoinments;
	}

	public void setAppoinments(List<Appointment> appoinments) {
		this.appoinments = appoinments;
	}

	@Override
	public String toString() {
		return "ServiceProvider [username="+ username +", name=" + name + ", email=" + email + "] \n" +services;
	}

	


	
	
	
	
}
