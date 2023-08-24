package com.scheduLink.dao;

import java.util.List;

import com.scheduLink.entity.Appointment;
import com.scheduLink.entity.Customer;
import com.scheduLink.entity.Service;
import com.scheduLink.entity.ServiceProvider;
import com.scheduLink.entity.ServiceSlot;
import com.scheduLink.exception.NoRecordFoundException;
import com.scheduLink.exception.SomethingWentWrongException;

public interface ProjectDao {
	
	public void registerCus(Customer customer, String query) throws SomethingWentWrongException;
	public Customer checkValidCus(String query, String loginEmail, String loginPass) throws NoRecordFoundException, SomethingWentWrongException;
	public void registerServiceProv(ServiceProvider serviceProvider, String query) throws SomethingWentWrongException;
	public ServiceProvider checkValidServ(String query, String loginUSerName, String loginPass) throws NoRecordFoundException, SomethingWentWrongException;
	 public List<ServiceProvider> viewServiceProviders(String query) throws SomethingWentWrongException;
	 public void addService(Service service) throws SomethingWentWrongException;
	 public void bookAppointment(ServiceSlot validSlot, ServiceProvider serviceProviders) throws SomethingWentWrongException;
	 public void cancelAppointment(Appointment Appointment)throws SomethingWentWrongException;
	 public List<Appointment> viewAppointmentSp(ServiceProvider sp)throws SomethingWentWrongException,NoRecordFoundException; 
	 public void giveService(Appointment ap,String status) throws SomethingWentWrongException ;
	public List<Service> viewServices(ServiceProvider serviceProvider) throws SomethingWentWrongException;
	public void addServiceSlot(Service service) throws SomethingWentWrongException;

}
