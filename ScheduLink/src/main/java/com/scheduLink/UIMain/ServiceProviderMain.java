package com.scheduLink.UIMain;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.scheduLink.entity.Appointment;
import com.scheduLink.entity.Feedback;
import com.scheduLink.entity.Service;
import com.scheduLink.entity.ServiceProvider;
import com.scheduLink.entity.ServiceSlot;
import com.scheduLink.exception.NoRecordFoundException;
import com.scheduLink.exception.SomethingWentWrongException;
import com.scheduLink.service.ProjectService;
import com.scheduLink.service.ProjectServiceImpl;

public class ServiceProviderMain {
	
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_PURPLE = "\u001B[34m";
	public static final String ANSI_BLUE = "\u001B[35m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_PINK = "\u001B[38;5;206m";
	
	public static void ServiceProviderRole(Scanner sc, ServiceProvider serviceProvider) {

		boolean isTrue = true;
		int choice = 0;
		do {
			System.out.println(ANSI_PINK + "Enter:-1 For Add new Services");
			System.out.println("Enter:-2 For View Appoinment Details");
			System.out.println("Enter:-3 For Give Service to Booked Appoinment");
			System.out.println("Enter:-4 For Open More Slots in Existing Service");
			System.out.println("Enter:-5 For Search the appointment by id");
			System.out.println("Enter:-6 View feedback for particular service");
			System.out.println("Enter:-0 For Logout" + ANSI_RESET);
			System.out.print(ANSI_YELLOW + "Enter Your Choice:- " + ANSI_RESET);
			try {
				choice = sc.nextInt();
			} catch (Exception e) {
				sc.nextLine();
				choice = 9;
			}
			switch (choice) {
			case 1 -> addService(sc, serviceProvider);
			case 2 -> viewAppointment(sc, serviceProvider);
			case 3 -> giveService(sc, serviceProvider);
			case 4 -> openSlot(sc, serviceProvider);
			case 5 -> searchAppointment(sc);
			case 6 -> viewFeedback(sc, serviceProvider);
			case 0 -> {
				isTrue = false;
				System.out.println();
				System.out.println(ANSI_GREEN + "Thanks Admin For Using Our Services!" + ANSI_RESET);
				System.out.println();
			}
			default -> System.out.println(ANSI_RED + "Invalid Choice! Please Choose Above Choice Only " + ANSI_RESET);
			}
			
		} while (isTrue);
	}

	
	public static void addService(Scanner sc , ServiceProvider serviceProvider) {
		sc.nextLine();
		try {
			 
			System.out.println(ANSI_BLUE+"Enter Service Title");
			String serviceTitle = sc.nextLine();
			System.out.println("Enter Service Description");
			String serviceDesc = sc.nextLine();
			
			Service service = new Service(serviceTitle,serviceDesc);
			
			System.out.println("How many Slot you want to add");
			int slot = sc.nextInt();
			
			Set<ServiceSlot> serivceSlots = new HashSet<>();
			
			for(int i =0 ;i <slot ;i++)
			{
				System.out.println("Enter Slot "+ (i+1)+ "time(hh->hh");
				String Slottime = sc.next();
				
				ServiceSlot serviceslot = new ServiceSlot(Slottime);
				
				serviceslot.setservice(service);
				
				serivceSlots.add(serviceslot);
		}
			service.setServiceSlots(serivceSlots);
			service.setSp(serviceProvider);
			
			ProjectService ps = new ProjectServiceImpl();
			ps.addService(service);
			System.out.println();
			System.out.println(ANSI_RESET+ANSI_PURPLE+"New Service Added SucessFully"+ANSI_RESET);
		}
		catch(Exception e)
		{
			System.out.println(ANSI_RED+e.getMessage()+ANSI_RESET);	
		}
			
		
	}
	
	public static void viewAppointment(Scanner sc ,ServiceProvider serviceProvider)
	{
		ProjectService ps = new ProjectServiceImpl();
		try {
			List<Appointment> appoinmentSp = ps.viewAppointmentSp(serviceProvider);
			appoinmentSp.forEach(a -> System.out.println(ANSI_BLUE + a +", Customer= "+a.getCustomerName()+", Booked At:- "+a.getBooked_at()+" ]"+ ANSI_RESET));
			System.out.println();
			
			
		}
		catch (SomethingWentWrongException | NoRecordFoundException e) {
			System.out.println(ANSI_RED + e.getMessage() + ANSI_RESET);
		}
		
	}
	
	public static void giveService(Scanner sc , ServiceProvider sp)
	{
		viewAppointment(sc, sp);
		System.out.println(ANSI_BLUE + "Enter Appoinment id from above in which you want to give Service:- ");
		ProjectService ps = new ProjectServiceImpl();
		
		int id = sc.nextInt();
		try {
			Appointment appoinment = ps.findAppointment(id);
			System.out.print("Enter Status You Want To Give (Processing/completed):- " + ANSI_RESET);
			String status = sc.next();
			ps.giveService(appoinment, status);
			System.out.println(ANSI_GREEN + "Appoinment Updated SucessFullly!" + ANSI_RESET);
		} catch (NoRecordFoundException | SomethingWentWrongException e) {
			System.out.println(ANSI_RED + e.getMessage() + ANSI_RESET);
		}
		
	}
	
	
	public static void openSlot(Scanner sc , ServiceProvider sp)
	{
		ProjectService ps  = new ProjectServiceImpl();
		try {
			List<Service> services  = ps.viewServices(sp);
			
			services.forEach(s->System.out.println(s));
			
			System.out.println(ANSI_YELLOW+"Enter Service Tittle From Above In Which You Want to Add Slot :- "+ANSI_RESET);
			sc.nextLine();
			
			String title= sc.nextLine();
			Service service = ps.findService(title);
			
			if(service!=null) {
				  System.out.print(ANSI_RED+"How Many New Slot You Want to add In this Service:- "+ANSI_RESET);
				  
				  int newSlot = sc.nextInt();
				  
				  Set<ServiceSlot> serviceSlots = service.getServiceSlots();
				  
				  for(int i = 0 ;i<newSlot;i++)
				  {
					System.out.println(ANSI_GREEN+"Enter Slot "+(i+1)+" time(hh -> hh):- "+ANSI_RESET);
					
					  String slotTime= sc.next();
					  
					  ServiceSlot slot = new ServiceSlot(slotTime);
			    	  serviceSlots.add(slot);
			    	  slot.setservice(service);
			    	  
				  }
			service.setServiceSlots(serviceSlots);
				  
			ps.addServiceSlot(service);	  
			}
			
			else {
				throw new NoRecordFoundException("NO Service Available With Given Id");
			}
			
			
		}
		catch(Exception e)
		{
			System.out.println(ANSI_RED+e.getMessage()+ANSI_RESET);
		}
	}
	
	
	public static void searchAppointment(Scanner sc) {
		System.out.print("Enter Appoinment id which you want to search:- ");
		int id = sc.nextInt();
		ProjectService ps = new ProjectServiceImpl();
		try {
			Appointment appoinment = ps.findAppointment(id);
			System.out.println(appoinment);
		} catch (NoRecordFoundException e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	public static void viewFeedback(Scanner sc , ServiceProvider sp)
	{
		String query  = "SELECT f from Feedback f where f.service=:username";
		
		ProjectService ps = new ProjectServiceImpl();
		try {
			Feedback fb =ps.viewFeedback(query, sp);
			System.out.println(fb.toString());
			
			
		} catch (SomethingWentWrongException | NoRecordFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		
		
	}
	
}
