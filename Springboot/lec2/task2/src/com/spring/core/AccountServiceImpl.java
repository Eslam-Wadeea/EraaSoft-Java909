package com.spring.core;

public class AccountServiceImpl implements UserService, AccountService {
	
	private PersonService personService;
    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

	@Override
	public void save(String name) {
		System.out.println("PersonService: Saving " + name);
		
	}

	@Override
	public void getSavePerson(String name) {
		// TODO Auto-generated method stub
		
	}

	

}
