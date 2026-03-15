package com.spring.core;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext applicationContext = 
				new ClassPathXmlApplicationContext("applicationContext.xml");
		
		PersonService personService = 
				applicationContext.getBean("PersonService" , PersonService.class);
		personService.save("eslam");
		
		PersonService personService1 = 
				applicationContext.getBean("PersonService" , PersonService.class);
		personService.update("eslam");
		
		ManagerService managerService = 
				applicationContext.getBean("ManagerService" , ManagerService.class);
		managerService.save("eslam");
		
		ManagerService managerService1 = 
				applicationContext.getBean("ManagerService" , ManagerService.class);
		managerService.update("eslam");

	}

}
