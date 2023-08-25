package com.scheduLink.dao;

import java.time.LocalDateTime;
import java.util.List;

import com.scheduLink.entity.Appointment;
import com.scheduLink.entity.Customer;
import com.scheduLink.entity.Feedback;
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
		EntityManager em = DBUitiles.getConnection();
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			em.persist(service);
			et.commit();
		} catch (Exception e) {
			throw new SomethingWentWrongException("Unable To add Service");
		} finally {
			em.close();
		}
	}

	@Override
	public void bookAppointment(ServiceSlot validSlot, ServiceProvider serviceProviders)
			throws SomethingWentWrongException {
		EntityManager em = null;
		EntityTransaction et = null;
		try {
			em = DBUitiles.getConnection();
			et = em.getTransaction();
			et.begin();
			validSlot.setIsAvailabe("no");;
			em.merge(serviceProviders);
			em.merge(validSlot);
			et.commit();
			
		} catch (Exception e) {
			
			if(et!=null)
			{
				et.rollback();
			}
			System.out.println(e.getMessage());
			throw new SomethingWentWrongException("Unable to Book Appointment");
			
		}finally {
			if(em!=null)
			{
				em.close();
			}
		}
		

	}

	@Override
	public void cancelAppointment(Appointment Appointment) throws SomethingWentWrongException {
		
		EntityManager em = null;
		EntityTransaction et = null;
		try {
			em= DBUitiles.getConnection();
			et = em.getTransaction();
			et.begin();
			int slot_id = Appointment.getSlotID();
			
			ServiceSlot serviceSlot = em.find(ServiceSlot.class, slot_id);
			
			serviceSlot.setIsAvailabe("yes");
			
//			em.persist(serviceSlot);
			em.merge(serviceSlot);
			
			String query = "DELETE from Appointment a where a.appoinmentId = : id";
			Query query2 = em.createQuery(query);
			
			query2.setParameter("id", Appointment.getAppoinmentId());
			
			query2.executeUpdate();
			
			et.commit();
			
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
			throw new SomethingWentWrongException("Unable to Cancel Appointment");
		}
		finally {
			em.close();
		}
		
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
	
		EntityManager em = null;
		EntityTransaction et = null;
		try {
			em = DBUitiles.getConnection();
			et = em.getTransaction();
			et.begin();
			ap.setStatus(status);
			ap.setResponse_at(LocalDateTime.now());
			em.merge(ap);
			et.commit();
		} catch (Exception e) {
			throw new SomethingWentWrongException("Unable to Updated Status");
		}
		
	}

	@Override
	public List<Service> viewServices(ServiceProvider serviceProvider) throws SomethingWentWrongException {
		List<Service> service = null;
		EntityManager em  = null;
		
		try {
			em = DBUitiles.getConnection();
			String query= "select s from Service s where s.sp=:sProvider";
			
			Query query2 = em.createQuery(query);
			query2.setParameter("sProvider", serviceProvider);
			service = query2.getResultList();
			
			
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
			throw new SomethingWentWrongException("Unable to Get Services!");
		}
		
		return service;
	}

	@Override
	public void addServiceSlot(Service service) throws SomethingWentWrongException {
		EntityManager em = DBUitiles.getConnection();
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			em.merge(service);
			et.commit();
			
			System.out.println("Slot added successfully");
		} catch (Exception e) {
			throw new SomethingWentWrongException("Unable To add Service");
		} finally {
			em.close();
		}

	}

	@Override
	public void provideFeedback(Feedback fb) throws SomethingWentWrongException {
		EntityManager em = DBUitiles.getConnection();
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
		
			
			em.persist(fb);
			et.commit();
			
			System.out.println("Provide feedback successfully");
		} catch (Exception e) {
			et.rollback();
			throw new SomethingWentWrongException("Unable To provide feedback");
		} finally {
			em.close();
		}
		
	}

	@Override
	public Feedback viewFeedback(String query, ServiceProvider usernmae)
			throws SomethingWentWrongException, NoRecordFoundException {
		
		EntityManager em = DBUitiles.getConnection();
		
		Feedback fb =null;
		try {
			
			Query q = em.createQuery(query);
			q.setParameter("username", usernmae);
			
			fb = (Feedback) q.getSingleResult();
			
			
			
			
			
		} catch (Exception e) {
			throw new SomethingWentWrongException(e.getMessage());
		} finally {
			em.close();
		}
		
		return fb;
	}

}
