package main.ewalletSystem.service.impl;

import main.ewalletSystem.helper.AccountResult;
import main.ewalletSystem.model.Account;
import main.ewalletSystem.service.AccountService;
import main.ewalletSystem.service.AccountValidationService;
import main.ewalletSystem.service.ApplicationService;

import java.util.Objects;
import java.util.Scanner;

public class EWalletServiceImpl implements ApplicationService {
    private AccountService accountService = new AccountServiceImpl();
    private AccountValidationService accountValidationService = new AccountValidationServiceImpl();

    @Override
    public void startApplication() {
        System.out.println("Welcome to EWalletSystem");
        boolean isExit = false;
        int count = 0;

        while (true) {
            System.out.println("please  enter your service");
            System.out.println("1.login     2.signup     3.exit");
            Scanner scanner = new Scanner(System.in);


            char service = scanner.next().charAt(0);
            switch (service) {
                case '1':
                    login();
                    break;
                case '2':
                    signup();
                    break;
                case '3':
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
        if (Objects.isNull(account)) {
            return;
        }
        boolean loginSuccess = accountService.getAccountByUserNameAndPassword(account);
        if (loginSuccess) {
            System.out.println("success login");
            profile(account);

        }else  {
            System.out.println("invalid username or password");
        }


    }
    private void signup() {

        Account account= getAccount(false);
        if (Objects.isNull(account)) {
            return;
        }
        boolean isAccountCreated = accountService.addAccount(account);
        if (isAccountCreated) {
            System.out.println("Account created successfully");
            profile(account);

        }else  {
            System.out.println("account already exists with that username");
        }


    } private Account getAccount(boolean login) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("please enter your username");
        String username = scanner.next();
        if(!accountValidationService.validateUserName(username)) {
            System.out.println("invalid username");
            return null;
        }
        System.out.println("please enter your password");
        String password = scanner.next();

        if(!accountValidationService.validatePassword(password)) {
            System.out.println("invalid password");
            return null;
        }
        if(login){
            return new Account(username, password );
        }



        System.out.println("please enter your phone number");
        String number = scanner.next();
        if(!accountValidationService.validatePhoneNumber(number)) {
            System.out.println("invalid number");
            return null;
        }
        System.out.println("please enter your address");
        String address = scanner.next();
        System.out.println("please enter your age");
        float age = scanner.nextFloat();
        if(!accountValidationService.validateAge(age)) {
            System.out.println("invalid age");
            return null;
        }


        return new Account(username, password, number, address, age);

    }
    private void profile(Account account) {
        boolean logout = false;
        int counter =0;
       while (true){
           System.out.println("services------->");
           System.out.println("1.deposit      2.withdraw     3.show account details     4.transfer money       5.change password      6.logout");
           Scanner scanner  = new Scanner(System.in);
           System.out.println("please give me your service");
           int result = scanner.nextInt();
           switch (result) {
               case 1:
                   deposit(account);
                   break;
               case 2:
                   withdraw(account);
                   break;
               case 3:
                   showAccountDetails(account);
                   break;
               case 4:
                   transferMoney(account);
                   break;
               case 5:
                   changePassword(account);
                   break;
               case 6:
                   System.out.println("have a nice day");
                   logout = true;
                   break;

               default:
                   System.out.println("invalid service");
                   counter++;
           }
           if (logout) {
               break;
           } else if (counter == 3) {
               System.out.println("please contact the administrator");
           }
       }

    }
    private void showAccountDetails(Account account) {
        Account accountExist = accountService.getAccountByUserName(account);
        if (Objects.isNull(accountExist)) {
            System.out.println("account does not exist");
            return;
        }
        System.out.println(account);

    }

    private void withdraw(Account account) {
        System.out.println("please enter amount to withdraw");
        Scanner scanner  = new Scanner(System.in);
        double amount = scanner.nextDouble();
        AccountResult withdrawSuccess = accountService.withdraw(account, amount);
            if (withdrawSuccess.getError() == 4) {
                System.out.println("success withdraw your balance "+ withdrawSuccess.getAmount());
            } else if (withdrawSuccess.getError()==3) {
                System.out.println("insufficient funds");
            } else if (withdrawSuccess.getError() == 2) {
                System.out.println("amount should be greater than 100");
            }else if (withdrawSuccess.getError() == 1) {
                System.out.println("account not found");
            }
    }

    private void deposit(Account account) {
        System.out.println("please enter amount to deposit");
        Scanner scanner  = new Scanner(System.in);
        double amount = scanner.nextDouble();
        AccountResult depositSuccess = accountService.deposit(account, amount);
        if (depositSuccess.getError() == 3) {
            System.out.println("success deposit successfully your balance "+  depositSuccess.getAmount());
        }else if (depositSuccess.getError() == 2) {
            System.out.println("amount should be greater than 100");
        }else if (depositSuccess.getError() == 1) {
            System.out.println("account not found");
        }
    }

    private void transferMoney(Account account) {
        System.out.println("please enter account user name you want to send to ");
        Scanner scanner  = new Scanner(System.in);
        String  usernameTo = scanner.next();

        System.out.println("Please enter the amount to transfer:");
        double amount = scanner.nextDouble();

        AccountResult transferSuccess = accountService.transferMoney(account , usernameTo , amount);
        if (transferSuccess.getError() == 1) {
            System.out.println("the sender account does not exist");
        }
        else if (transferSuccess.getError() == 2) {
            System.out.println("the receiver account does not exist");
        }

        else if (transferSuccess.getError() == 3) {
            System.out.println("insufficient funds");
        }
        else if (transferSuccess.getError() == 4) {
            System.out.println("amount should be greater than 100");
        }
        else if (transferSuccess.getError() == 5) {
            System.out.println("transfer successfully your balance "+ transferSuccess.getAmount());
        }
    }

    private void changePassword(Account account ) {
        System.out.println("please enter your current password");
        Scanner scanner = new Scanner(System.in);
        String currentPassword = scanner.next();
        if (!currentPassword.equals(account.getPassword())) {
            System.out.println("wrong current password");
            return;
        }
        System.out.println("please enter your new password");
        String newPassword = scanner.next();
        if (currentPassword.equals(newPassword)) {
            System.out.println("same current password");
            return;

        } else if (!accountValidationService.validatePassword(newPassword)) {
            System.out.println("invalid new password format");
            return;
        }
        System.out.println("change password success");
        accountService.changePassword(account, newPassword);
    }
}
