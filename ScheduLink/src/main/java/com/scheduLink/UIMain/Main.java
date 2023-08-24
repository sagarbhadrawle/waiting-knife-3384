package com.scheduLink.UIMain;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.scheduLink.entity.Customer;
import com.scheduLink.entity.ServiceProvider;
import com.scheduLink.exception.NoRecordFoundException;
import com.scheduLink.exception.SomethingWentWrongException;
import com.scheduLink.service.ProjectService;
import com.scheduLink.service.ProjectServiceImpl;
import com.scheduLink.utility.DBUitiles;

import jakarta.persistence.EntityManager;

public class Main {
	
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_PURPLE = "\u001B[34m";
	public static final String ANSI_BLUE = "\u001B[35m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_PINK = "\u001B[38;5;206m";
	public static final String ANSI_BLACK = "\u001B[30m";

	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		EntityManager em  = DBUitiles.getConnection();
		System.out.println("\n");
		System.out.println(ANSI_BLACK+"#########################################"+ANSI_RESET);
		System.out.println(ANSI_GREEN + "*********WELCOME TO ScheduLink*********" + ANSI_RESET);
		System.out.println(ANSI_BLACK+"#########################################"+ANSI_RESET);
		int choice = 0;
		do {
			try {
				System.out.println(ANSI_PINK + "Enter:-1 For Login as Customer");
				System.out.println("Enter:-2 For Login as Service Provider");
				System.out.println("Enter:-3 For Register as Customer");
				System.out.println("Enter:-4 For Register as Service Provider");
				System.out.println("Enter:-0 For EXIT" + ANSI_RESET);
				System.out.print(ANSI_YELLOW + "Enter Your Choice:- " + ANSI_RESET);
				choice = sc.nextInt();

			} catch (InputMismatchException e) {
				sc.nextLine();
				choice = 9;
			}
			switch (choice) {
			case 1 -> customerLogin(sc);
			case 2 -> ServiceLogin(sc);
			case 3 -> customerRegister(sc);
			case 4 -> registerServiceProvider(sc);
			case 0 -> System.out.println(ANSI_GREEN + "Thanks For Using Our Services" + ANSI_RESET);
			default -> System.out.println(ANSI_RED + "Invalid Choice! Please Choose Above Choice Only"+ ANSI_RESET);
			}
		} while (choice != 0);

		em.close();
	}
		
	
	public static void customerRegister(Scanner sc)
	{
				
		System.out.println();
		System.out.println(ANSI_PURPLE + "Welcome To Schedulink! Please Register" + ANSI_RESET);
		String  full_name;
		String  username;
		String 	password;
		String 	email;
		String phone_number;
		
		try {
			sc.nextLine();
			System.out.println(ANSI_GREEN + "Enter Your Full Name");
			full_name = sc.nextLine();
			System.out.println("Enter Your Email");
			email = sc.next();
			System.out.println("Enter a Username"  );
			username = sc.next();
			System.out.println("Enter a Strong Password"  );
			password = sc.next();
			System.out.println("Enter a Phone number"  );
			phone_number = sc.next();
			
			Customer customer = new Customer(username, password, full_name,email,phone_number);
			
			String query = "SELECT c FROM Customer c WHERE email=:login_email";
			
			ProjectService projectService = new ProjectServiceImpl();
			
			projectService.registerCustomer(customer, query);
			System.out.println(ANSI_RESET);
			System.out.println(ANSI_BLUE + "***Welcome To ScheduLink*** ThankYou For Registration "+customer.getFull_name() + ANSI_RESET);
			System.out.println();
		} catch (SomethingWentWrongException e) {
			System.out.println(ANSI_RED + e.getMessage() + ANSI_RESET);
		}

	}
	
	
	public static void registerServiceProvider(Scanner sc)
	{
				
		System.out.println();
		System.out.println(ANSI_PURPLE + "Welcome To Schedulink! Please Register as Service Provider " + ANSI_RESET);
		
		String  username;
		String 	name;
		String 	password;
		String 	email;
		
		
		try {
			sc.nextLine();
			System.out.println(ANSI_GREEN + "Enter Your Full Name");
			name = sc.nextLine();
			System.out.println("Enter Your Email");
			email = sc.next();
			System.out.println("Enter a Username"  );
			username = sc.next();
			System.out.println("Enter a Strong Password"  );
			password = sc.next();
			
			
			ServiceProvider serviceProv = new ServiceProvider(name, email, username,password);
			
			String query = "SELECT s FROM ServiceProvider s WHERE email=:login_email";
			
			ProjectService projectService = new ProjectServiceImpl();
			
			projectService.registerServiceProv(serviceProv, query);
			System.out.println(ANSI_RESET);
			System.out.println(ANSI_BLUE + "***Welcome To ScheduLink*** ThankYou For Registration "+serviceProv.getName() + ANSI_RESET);
			System.out.println();
		} catch (SomethingWentWrongException e) {
			System.out.println(ANSI_RED + e.getMessage() + ANSI_RESET);
		}

	}
	
	public static void customerLogin(Scanner sc)
	{
				
		System.out.println();
		System.out.println(ANSI_YELLOW + "Hii User! Please Login Using Correct Credentials" + ANSI_RESET);
		String loginUserName;
		String loginPass;
		try {
			System.out.println(ANSI_GREEN + "Enter Your Username ");
			loginUserName = sc.next();
			System.out.println("Enter Your Login Password");
			loginPass = sc.next();

			String query = "SELECT c FROM Customer c WHERE username=:login_username AND password=:login_password";
			ProjectService projectService = new ProjectServiceImpl();
			Customer customer = projectService.checkValidCus(query, loginUserName, loginPass);
			if(customer==null) {
			throw new NoRecordFoundException("No recrod with given username");
			}
			System.out.println();
			System.out.println(ANSI_GREEN+"**********************************************************************"+ANSI_RESET);
			System.out.println(
					ANSI_RESET + ANSI_BLUE + "Welcome " + customer.getFull_name() + " Happy to see you again" + ANSI_RESET);
			System.out.println(ANSI_GREEN+"************************************************************************"+ANSI_RESET);
			
			CustomerMain.customerRole(sc,customer);
		} catch (Exception e) {
			System.out.println(ANSI_RED + e.getMessage() + ANSI_RESET);
		}
	}
	
	
	
	public static void ServiceLogin(Scanner sc)
	{
				
		System.out.println();
		System.out.println(ANSI_YELLOW + "Hii Service Provider! Please Login Using Correct Credentials" + ANSI_RESET);
		String loginUserName;
		String loginPass;
		try {
			System.out.println(ANSI_GREEN + "Enter Your Login UserName");
			loginUserName = sc.next();
			System.out.println("Enter Your Login Password");
			loginPass = sc.next();

			String query = "SELECT s FROM ServiceProvider s WHERE username=:login_username AND password=:login_password";
			ProjectService projectService = new ProjectServiceImpl();
			ServiceProvider serviceProvider = projectService.checkValidServ(query, loginUserName, loginPass);
			if(serviceProvider==null) {
			throw new NoRecordFoundException("No recrod with given username");
			}
			System.out.println();
			System.out.println(ANSI_GREEN+"**********************************************************************"+ANSI_RESET);
			System.out.println(
					ANSI_RESET + ANSI_BLUE + "Welcome " + serviceProvider.getName() + " Happy to see you again" + ANSI_RESET);
			System.out.println(ANSI_GREEN+"************************************************************************"+ANSI_RESET);
			ServiceProviderMain.ServiceProviderRole(sc, serviceProvider);
			
		} catch (Exception e) {
			System.out.println(ANSI_RED + e.getMessage() + ANSI_RESET);
		}
	}
	
	
	
	
}
