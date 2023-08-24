package com.scheduLink.utility;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DBUitiles {
	
	static  EntityManagerFactory emf; 
	
	static {
		
		emf = Persistence.createEntityManagerFactory("Schedulink");
	}
	
	public static EntityManager getConnection()
	{
		return emf.createEntityManager();
	}
	
}
