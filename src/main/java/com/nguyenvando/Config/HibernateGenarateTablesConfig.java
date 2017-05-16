/**
 * 
 */
package com.nguyenvando.Config;

import java.util.HashSet;
import java.util.Set;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.nguyenvando.Entities.City;
import com.nguyenvando.Entities.Course;
import com.nguyenvando.Entities.District;
import com.nguyenvando.Entities.School;
import com.nguyenvando.Entities.User;
import com.nguyenvando.Entities.UserRole;

/**
 *
 * @author Nguyen Van Do
 * @DateBegin Mar 19, 2017 1:49:17 PM
 *
 **/
public class HibernateGenarateTablesConfig {
	
	private static SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;
	
	public static void main(String[] args) {
	
		try {
			
				Configuration configuration=new Configuration();
				configuration.addResource("hibernate.cfg.xml");
				configuration.configure();
				serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
				sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		  
	
		
		
		}
			catch (Exception e) {
		        System.err.println("Error creating Session: " + e);
		        throw new ExceptionInInitializerError(e);
			}
	}
	
	public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }	


	
	
}
