package com.scheduLink.service;

import java.util.List;

import com.scheduLink.entity.Appointment;
import com.scheduLink.entity.Customer;
import com.scheduLink.entity.Feedback;
import com.scheduLink.entity.Service;
import com.scheduLink.entity.ServiceProvider;
import com.scheduLink.entity.ServiceSlot;
import com.scheduLink.exception.NoRecordFoundException;
import com.scheduLink.exception.SomethingWentWrongException;

public interface ProjectService {
		
	public void registerCustomer(Customer customer, String query) throws SomethingWentWrongException;
	public Customer checkValidCus(String query, String username, String loginPass) throws NoRecordFoundException, SomethingWentWrongException;
	public void registerServiceProv(ServiceProvider serviceProvider, String query) throws SomethingWentWrongException;
	public ServiceProvider checkValidServ(String query, String loginUSerName, String loginPass) throws NoRecordFoundException, SomethingWentWrongException;
   public List<ServiceProvider> viewServiceProviders(String query) throws SomethingWentWrongException;
   public void addService(Service service) throws SomethingWentWrongException;
   public ServiceProvider findServiceProvider(String userName) throws NoRecordFoundException;
   public Service findService(String title) throws NoRecordFoundException;
   public ServiceSlot findValidSlot(int id) throws NoRecordFoundException;
   public void bookAppointment(ServiceSlot validSlot, ServiceProvider sp) throws SomethingWentWrongException;
   public List<Appointment> showAppointment(Customer customer,String query) throws SomethingWentWrongException,NoRecordFoundException;
   public Appointment findAppointment(int id) throws NoRecordFoundException;
   public void cancelAppointment(Appointment Appointment)throws SomethingWentWrongException;
   public List<Appointment> viewAppointmentSp(ServiceProvider sp)throws SomethingWentWrongException,NoRecordFoundException; 
   public void giveService(Appointment ap,String status)throws SomethingWentWrongException;
   public List<Service> viewServices(ServiceProvider serviceProvider) throws SomethingWentWrongException;
   public void addServiceSlot(Service service) throws SomethingWentWrongException;	
   public void provideFeedback(Feedback fb) throws SomethingWentWrongException;
   public Feedback viewFeedback(String query,ServiceProvider usernmae) throws SomethingWentWrongException,NoRecordFoundException;

}
	