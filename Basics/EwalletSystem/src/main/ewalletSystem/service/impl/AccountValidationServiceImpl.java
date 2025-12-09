package main.ewalletSystem.service.impl;

import main.ewalletSystem.service.AccountValidationService;

public class AccountValidationServiceImpl implements AccountValidationService {

    @Override
    public boolean validateUserName(String userName) {
        if (userName.length() < 3) {
            System.out.println("Username is less than 3 characters");
            return false;
        }
        for (int i = 0; i < userName.length() ; i++) {
            char character = userName.charAt(i);
            if (!(character >= 'a' && character <= 'z' ||  character >= 'A' && character <= 'Z')) {
                System.out.println("user name must have only alphabet characters");
                return false;
            }
        }
        if(!(userName.charAt(0) >='A' && userName.charAt(0) <='Z')) {
            System.out.println("user name must start with an uppercase letter");
            return false;
        }
        return true;
    }

    @Override
    public boolean validatePassword(String password) {
        if (password.length() < 8) {
            System.out.println("Password is less than 8 characters");
            return false;
        }
        boolean hasUpperCase = false;
        boolean hasLowerCase = false;
        boolean hasSpecial = false;
        boolean hasDigit = false;

        for (int i = 0; i < password.length() ; i++) {
            char character = password.charAt(i);
            if (character >= 'A' && character <= 'Z') {
                hasUpperCase = true;
            }
            else if (character >= 'a' && character <= 'z') {
                hasLowerCase = true;
            }
            else if (character >= '0' && character <= '9') {
                hasDigit = true;
            }
            else if (character >= '#' && character <= '&' || character == '@') {
                hasSpecial = true;
            }
            if (hasDigit && hasUpperCase && hasLowerCase && hasSpecial) {
                return true;
            }
        }
        System.out.println("password must contain at least one uppercase character,one lowercase character,one digit and one special character(#,$,%,@,&))  ");
        return false;
    }

    @Override
    public boolean validatePhoneNumber(String phoneNumber) {
        if (phoneNumber.length()!=13) {
            System.out.println("Phone number must be 13 characters and starts with +2");
            return false;
        }
        if (phoneNumber.startsWith("+2")) {
            return true;
        }
        return false;

    }

    @Override
    public boolean validateAge(float age) {
        if (age < 18) {
            System.out.println("Age must be greater than 18");
            return false;
        }
        return true;
    }


}

