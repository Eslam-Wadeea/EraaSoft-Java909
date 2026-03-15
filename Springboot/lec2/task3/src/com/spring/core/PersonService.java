package com.spring.core;

public class PersonService implements UserService {
	
	public void myInitMethod() {
        System.out.println("PersonService: Bean is being initialized...");
    }
	
	public void myDestroyMethod() {
        System.out.println("PersonService: Bean is being destroyed...");
    }

	@Override
	public void save(String name) {
		System.out.println("start save method on person service");
		
	}
	
	

	

}
