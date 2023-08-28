
package com.scheduLink.entity;

import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Appointment {
		
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int appoinmentId;
	private String customerName;
	private String serviceName;
	private String slot;
	private int slotID;
	private String status;
	private LocalDateTime booked_at;
	private LocalDateTime response_at;
	
	
	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "Customer_id")
	private Customer customer;
	
	
	@ManyToOne(cascade = CascadeType.DETACH )
	@JoinColumn(name="ServiceProviderUsername")
	private ServiceProvider serviceProvider;
	
	public Appointment() {
		// TODO Auto-generated constructor stub
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getSlot() {
		return slot;
	}

	public void setSlot(String slot) {
		this.slot = slot;
	}

	public int getSlotID() {
		return slotID;
	}

	public void setSlotID(int slotID) {
		this.slotID = slotID;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getBooked_at() {
		return booked_at;
	}

	public void setBooked_at(LocalDateTime booked_at) {
		this.booked_at = booked_at;
	}

	public LocalDateTime getResponse_at() {
		return response_at;
	}

	public void setResponse_at(LocalDateTime response_at) {
		this.response_at = response_at;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public ServiceProvider getServiceProvider() {
		return serviceProvider;
	}

	public void setServiceProvider(ServiceProvider serviceProvider) {
		this.serviceProvider = serviceProvider;
	}

	public int getAppoinmentId() {
		return appoinmentId;
	}

	public Appointment(String customerName, String serviceName, String slot, int slotID, String status,
			LocalDateTime booked_at, LocalDateTime response_at, Customer customer, ServiceProvider serviceProvider) {
		super();
		this.customerName = customerName;
		this.serviceName = serviceName;
		this.slot = slot;
		this.slotID = slotID;
		this.status = status;
		this.booked_at = booked_at;
		this.response_at = response_at;
		this.customer = customer;
		this.serviceProvider = serviceProvider;
	}
//
//	@Override
//	public String toString() {
//		return "Appointment [appoinmentId=" + appoinmentId + ", customerName=" + customerName + ", serviceName="
//				+ serviceName + ", slot=" + slot + ", slotID=" + slotID + ", status=" + status  +"]"; 
//	}

	@Override
	public String toString() {
		return String.format(
				"Appointment [appoinmentId=%s, customerName=%s, serviceName=%s, slot=%s, slotID=%s, status=%s]",
				appoinmentId, customerName, serviceName, slot, slotID, status);
	}
	
	
	
	
	
}
