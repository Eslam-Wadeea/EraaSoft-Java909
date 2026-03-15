package com.spring.core;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext applicationContext = 
				new ClassPathXmlApplicationContext("applicationContext.xml");
		
		AccountServiceImpl accountServiceImpl = 
				applicationContext.getBean("AccountServiceImpl" , AccountServiceImpl.class);
		accountServiceImpl.save("eslam");
		
		

	}

}
