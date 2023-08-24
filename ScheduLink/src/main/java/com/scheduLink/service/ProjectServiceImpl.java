package com.scheduLink.service;

import java.util.List;

import com.scheduLink.dao.ProjectDao;
import com.scheduLink.dao.ProjectDaoImpl;
import com.scheduLink.entity.Appointment;
import com.scheduLink.entity.Customer;
import com.scheduLink.entity.Service;
import com.scheduLink.entity.ServiceProvider;
import com.scheduLink.entity.ServiceSlot;
import com.scheduLink.exception.NoRecordFoundException;
import com.scheduLink.exception.SomethingWentWrongException;
import com.scheduLink.utility.DBUitiles;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public class ProjectServiceImpl implements ProjectService {

	@Override
	public void registerCustomer(Customer customer, String query) throws SomethingWentWrongException {
		
		ProjectDao  pj = new ProjectDaoImpl();
		pj.registerCus(customer, query);

	}

	@Override
	public Customer checkValidCus(String query, String username, String loginPass)
			throws NoRecordFoundException, SomethingWentWrongException {
		
		ProjectDao  pj = new ProjectDaoImpl();
		 
		Customer c =pj.checkValidCus(query, username, loginPass);
		
		return c; 
	}

	@Override
	public void registerServiceProv(ServiceProvider serviceProvider, String query) throws SomethingWentWrongException {
		ProjectDao  pj = new ProjectDaoImpl();
		pj.registerServiceProv(serviceProvider, query);
	}

	@Override
	public ServiceProvider checkValidServ(String query, String loginUSerName, String loginPass)
			throws NoRecordFoundException, SomethingWentWrongException {
	
		ProjectDao  pj = new ProjectDaoImpl();
		
		
		return  pj.checkValidServ(query, loginUSerName, loginPass);
	}

	@Override
	public List<ServiceProvider> viewServiceProviders(String query) throws SomethingWentWrongException {
		ProjectDao  pj = new ProjectDaoImpl();
		return pj.viewServiceProviders(query);
		
		
		
	}

	@Override
	public void addService(Service service) throws SomethingWentWrongException {
		// TODO Auto-generated method stub

	}

	@Override
	public ServiceProvider findServiceProvider(String userName) throws NoRecordFoundException {
		
		ServiceProvider sp =null;
		EntityManager em = null;
		try {
			em = DBUitiles.getConnection();
			String query  = "SELECT s FROM ServiceProvider s where  username =:username ";
			Query q = em.createQuery(query);
			q.setParameter("username", userName);
			
			 sp = (ServiceProvider) q.getSingleResult();
			
			if(sp==null) throw new NoRecordFoundException("no record  found with given username");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			em.close();
		}
		
		return sp;
	}

	@Override
	public Service findService(String title) throws NoRecordFoundException {
		
		EntityManager em = null;
		Service service = null;
		try {
			em= DBUitiles.getConnection();
			
			service = em.find(Service.class, title);
			
			if(service==null)
			{
				throw new NoRecordFoundException("Service With Given Title Not Available");	
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			em.close();
		}
		
		
		return service;
	}

	@Override
	public ServiceSlot findValidSlot(int id) throws NoRecordFoundException {
		EntityManager em = null;
		ServiceSlot slot = null;
		try {
			em= DBUitiles.getConnection();
			
			slot = em.find(ServiceSlot.class, id);
			
			if(slot==null)
			{
				throw new NoRecordFoundException("Service With Given Title Not Available");	
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			em.close();
		}
		
		
		return slot;
	}

	@Override
	public void bookAppointment(ServiceSlot validSlot, ServiceProvider sp) throws SomethingWentWrongException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Appointment> showAppointment(Customer customer, String query)
			throws SomethingWentWrongException, NoRecordFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Appointment findAppointment(int id) throws NoRecordFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void cancelAppointment(Appointment Appointment) throws SomethingWentWrongException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Appointment> viewAppointmentSp(ServiceProvider sp)
			throws SomethingWentWrongException, NoRecordFoundException {
		
		ProjectDao projectDao = new ProjectDaoImpl(); 
		return projectDao.viewAppointmentSp(sp);
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
