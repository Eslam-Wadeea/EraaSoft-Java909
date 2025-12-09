package main.ewalletSystem.service;

public interface AccountValidationService {

    boolean validateUserName(String userName);
    boolean validatePassword(String password);
    boolean validatePhoneNumber(String phoneNumber);
    boolean validateAge(float age);
}
