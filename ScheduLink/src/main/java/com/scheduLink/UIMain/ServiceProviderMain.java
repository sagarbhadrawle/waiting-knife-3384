package com.scheduLink.UIMain;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.scheduLink.entity.Appointment;
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

	public static void ServiceProviderRole(Scanner sc, ServiceProvider serviceProvider) {

		boolean isTrue = true;
		int choice = 0;
		do {
			System.out.println(ANSI_PURPLE + "Enter:-1 For Add new Services");
			System.out.println("Enter:-2 For View Appoinment Details");
			System.out.println("Enter:-3 For Give Service to Booked Appoinment");
			System.out.println("Enter:-4 For Open More Slots in Existing Service");
			System.out.println("Enter:-5 For Search the appointment by id");
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
			case 2 -> viewAppoi(sc, serviceProvider);
//			case 3 -> giveService(sc, serviceProvider);
//			case 4 -> openSlot(sc, serviceProvider);
//			case 5 -> searchAppoi(sc);
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
	
	public static void viewAppoi(Scanner sc ,ServiceProvider serviceProvider)
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
	
	
	
}
