package com.spring.core;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext applicationContext = 
				new ClassPathXmlApplicationContext("applicationContext.xml");
		
			UserService s1 = applicationContext.getBean("myPersonService", UserService.class);
        
        
			UserService s2 = applicationContext.getBean("myPersonService", UserService.class);
			System.out.println( (s1 == s2)); 

			s1.save("eslam");

			applicationContext.close();
    }
		
		

	}


