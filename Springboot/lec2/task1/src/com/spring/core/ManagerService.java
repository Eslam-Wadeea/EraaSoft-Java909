package com.spring.core;

public class ManagerService implements UserService{

	@Override
	public void save(String name) {
		System.out.println("start save method on ManagerService");
		
	}

	@Override
	public void update(String name) {
		System.out.println("start upadte method on ManagerService");
		
	}

}
