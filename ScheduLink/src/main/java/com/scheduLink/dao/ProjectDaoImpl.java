package com.scheduLink.dao;

import java.util.List;

import com.scheduLink.entity.Appointment;
import com.scheduLink.entity.Customer;
import com.scheduLink.entity.Service;
import com.scheduLink.entity.ServiceProvider;
import com.scheduLink.entity.ServiceSlot;
import com.scheduLink.exception.NoRecordFoundException;
import com.scheduLink.exception.SomethingWentWrongException;
import com.scheduLink.utility.DBUitiles;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;

public class ProjectDaoImpl implements ProjectDao {
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_RESET = "\u001B[0m";

	
	@Override
	public void registerCus(Customer customer, String query) throws SomethingWentWrongException {
		
		EntityManager em = DBUitiles.getConnection();
		EntityTransaction et = em.getTransaction();
		
		try {
			
			Query q1 = em.createQuery(query);
			
			q1.setParameter("login_email", customer.getEmail());
			
			
			
			try {
				Customer customer1 = (Customer) q1.getSingleResult();
				System.out.println(
						ANSI_RED + "You are Already Registerd " + customer1.getFull_name() + " Please Login" + ANSI_RESET);
				
				
			} catch (NoResultException e) {
				et.begin();
				em.persist(customer);
				et.commit();
				
			}
			
			
			
		} catch (Exception e) {
			et.rollback();
			throw new SomethingWentWrongException("Unable to Register! Please Try Again");
		}
		finally {
			em.close();
		}
		
		
	}

	@Override
	public Customer checkValidCus(String query, String username, String loginPass)
			throws NoRecordFoundException, SomethingWentWrongException {
		
		EntityManager em = DBUitiles.getConnection();
		Customer customer = null;
		try {
			
			Query q1 = em.createQuery(query);
			
			q1.setParameter("login_username", username);
			q1.setParameter("login_password", loginPass);
			customer	= (Customer) q1.getSingleResult();
			
			
			
		} catch (IllegalArgumentException | IllegalStateException e) {
			
			throw new SomethingWentWrongException("Unable to Login! Please try again");
		}
		catch(NoResultException e)
		{
			System.out.println(ANSI_RED + "You Are Not Registerd! Please Register First" + ANSI_RESET);
		}
		
		finally {
			em.close();
		}
		
		
		
		return customer;
	}

	@Override
	public void registerServiceProv(ServiceProvider serviceProvider, String query) throws SomethingWentWrongException {
		
		EntityManager em = DBUitiles.getConnection();
		EntityTransaction et = em.getTransaction();
		
		try {
			
			Query q1 = em.createQuery(query);
			
			q1.setParameter("login_email", serviceProvider.getEmail());
			
			
			
			try {
				ServiceProvider serviceProvider1 = (ServiceProvider) q1.getSingleResult();
				System.out.println(
						ANSI_RED + "You are Already Registerd " + serviceProvider1.getName() + " Please Login" + ANSI_RESET);
				
				
			} catch (NoResultException e) {
				et.begin();
				em.persist(serviceProvider);
				et.commit();
				
			}
			
			
			
		} catch (Exception e) {
			et.rollback();
			throw new SomethingWentWrongException("Unable to Register! Please Try Again");
		}
		finally {
			em.close();
		}

	}

	@Override
	public ServiceProvider checkValidServ(String query, String loginUSerName, String loginPass)
			throws NoRecordFoundException, SomethingWentWrongException {
		EntityManager em = DBUitiles.getConnection();

		ServiceProvider s = null;
		try {
			
			Query q1 = em.createQuery(query);
			
			q1.setParameter("login_username", loginUSerName);
			q1.setParameter("login_password", loginPass);
			 s = (ServiceProvider) q1.getSingleResult();
			
			
			
		} catch (IllegalArgumentException | IllegalStateException e) {
			
			throw new SomethingWentWrongException("Unable to Login! Please try again");
		}
		catch(NoResultException e)
		{
			System.out.println(ANSI_RED + "You Are Not Registerd! Please Register First" + ANSI_RESET);
		}
		
		finally {
			em.close();
		}
	return  s;
	}

	@Override
	public List<ServiceProvider> viewServiceProviders(String query) throws SomethingWentWrongException {
		
		EntityManager em = null;
		List<ServiceProvider> service = null;
		try {
			em = DBUitiles.getConnection();
			
			Query q = em.createQuery(query);
			
			service = q.getResultList();
			
			
			
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
			throw new SomethingWentWrongException("Unable to view Service Providers Details");
		}
		finally {
			em.close();
		}
		
		return service;
	}

	@Override
	public void addService(Service service) throws SomethingWentWrongException {
		// TODO Auto-generated method stub

	}

	@Override
	public void bookAppointment(ServiceSlot validSlot, ServiceProvider serviceProviders)
			throws SomethingWentWrongException {
		// TODO Auto-generated method stub

	}

	@Override
	public void cancelAppointment(Appointment Appointment) throws SomethingWentWrongException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Appointment> viewAppointmentSp(ServiceProvider sp)
			throws SomethingWentWrongException, NoRecordFoundException {
		
		EntityManager em = null;
		List<Appointment> appointment = null;
		try {
			
			String query = "SELECT a from Appointment a where serviceProvider =:sp ";
			em = DBUitiles.getConnection();
			Query query2 = em.createQuery(query); 
			query2.setParameter("sp", sp);
			appointment =   query2.getResultList();
			if(appointment.size()==0) {
				throw new NoRecordFoundException("No Appoinment Recevied");
			}
			
			
		} catch (Exception e) {
			throw new SomethingWentWrongException(e.getMessage());
		}
		
		return appointment;
	}

	@Override
	public void giveService(Appointment ap, String status) throws SomethingWentWrongException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Service> viewServices(ServiceProvider serviceProvider) throws SomethingWentWrongException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addServiceSlot(Service service) throws SomethingWentWrongException {
		// TODO Auto-generated method stub

	}

}
