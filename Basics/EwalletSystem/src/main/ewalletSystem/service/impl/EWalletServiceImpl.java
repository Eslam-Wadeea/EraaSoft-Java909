package main.ewalletSystem.service.impl;

import main.ewalletSystem.model.Account;
import main.ewalletSystem.service.AccountService;
import main.ewalletSystem.service.ApplicationService;

import java.util.Scanner;

public class EWalletServiceImpl implements ApplicationService {
    private AccountService accountService = new AccountServiceImpl();

    @Override
    public void startApplication() {
        System.out.println("welcome sir");
        System.out.println("Welcome to EWalletSystem");
        boolean isExit = false;
        int count = 0;

        while (true) {
            System.out.println("please  enter your service");
            System.out.println("a.login     b.signup     c.exit");
            Scanner scanner = new Scanner(System.in);


            char service = scanner.next().charAt(0);
            switch (service) {
                case 'a':
                    login();
                    break;
                case 'b':
                    signup();
                    break;
                case 'c':
                    System.out.println("have a nice day");
                    isExit = true;
                    break;
                default:
                    System.out.println("invalid service");
                    count++;
            }
            if (isExit) {
                break;
            }
            if (count == 3) {
                System.out.println("please contact the administrator");
                break;
            }



        }
    }

    private void login() {
        Account account= getAccount(true);
        boolean loginSuccess = accountService.getAccount(account);
        if (loginSuccess) {
            System.out.println("success login");
        }else  {
            System.out.println("invalid username or password");
        }


    }
    private void signup() {

        Account account= getAccount(false);
        boolean isAccountCreated = accountService.addAccount(account);
        if (isAccountCreated) {
            System.out.println("Account created successfully");

        }else  {
            System.out.println("account already exists with that username");
        }


    } private Account getAccount(boolean login) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("please enter your username");
        String username = scanner.next();
        System.out.println("please enter your password");
        String password = scanner.next();

        if(login){
            return new Account(username, password);
        }

        System.out.println("please enter your phone number");
        String number = scanner.next();
        System.out.println("please enter your address");
        String address = scanner.next();
        System.out.println("please enter your age");
        float age = scanner.nextFloat();

        return new Account(username, password, number, address, age);

    }
}
