package com.spring.core;

public class PersonService implements UserService {

	@Override
	public void save(String name) {
		System.out.println("start save method on PersonService");
		
	}

	@Override
	public void update(String name) {
		System.out.println("start upadte method on PersonService");
		
	}

}
