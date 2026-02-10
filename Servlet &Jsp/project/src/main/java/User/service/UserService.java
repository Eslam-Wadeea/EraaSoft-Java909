package User.service;

import User.model.User;

public interface UserService {
	boolean login(String email, String password);
	boolean signup(User user);
	
}
