package main.ewalletSystem.service.impl;

import main.ewalletSystem.helper.AccountResult;
import main.ewalletSystem.model.Account;
import main.ewalletSystem.model.History;
import main.ewalletSystem.service.AccountService;
import main.ewalletSystem.service.AccountValidationService;
import main.ewalletSystem.service.ApplicationService;
import main.ewalletSystem.service.HistoryService;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
public class EWalletServiceImpl implements ApplicationService {
    private final AccountService accountService = new AccountServiceImpl();
    private final AccountValidationService accountValidationService = new AccountValidationServiceImpl();
    private final HistoryService historyService = new HistoryServiceImpl();

    @Override
    public void startApplication() {
        createAdmin();
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
        Account loginSuccess = accountService.getAccountByUserNameAndPassword(account);
        if (loginSuccess.isActive() && !loginSuccess.isDeleted()) {
            System.out.println("success login");
            historyService.addHistory(
                    account.getUsername(),
                    "LOGIN",
                    0,
                    " success login"
            );

            profile(account);

        }else if(!loginSuccess.isActive())  {
            System.out.println("account is deactivated");
        }
        else if(loginSuccess.isDeleted())  {
            System.out.println("account is deleted");
        }
        else {
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
            historyService.addHistory(
                    account.getUsername(),
                    "SIGNUP",
                    0,
                    "Account created"
            );
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
            return new Account(username, password ,false , true , false);


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


        return new Account(username, password, number, address, age ,false , true );

    }
    private void profile(Account account) {
        boolean logout = false;
        int counter =0;
       while (true){
           System.out.println("services------->");
           System.out.println("please give me your service");
           System.out.println("1.deposit      2.withdraw     3.show account details     4.transfer money       5.change password        6.history          7.logout");
           Scanner scanner  = new Scanner(System.in);
           if(Objects.equals(account.getUsername(), "Admin")){
               account.setAdmin(true);
               System.out.println("8.Admin panel");
           }
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
                   history(account);
                   break;
               case 7:
                   System.out.println("have a nice day");
                   logout = true;
                   break;
               case 8 :
                   adminPanel(account);
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
                historyService.addHistory(
                        account.getUsername(),
                        "WITHDRAW", amount, "success"
                );
            } else if (withdrawSuccess.getError()==3) {
                System.out.println("insufficient funds");
                historyService.addHistory(
                        account.getUsername(),
                        "WITHDRAW", amount, "failed due to insufficient funds"
                );
            } else if (withdrawSuccess.getError() == 2) {
                System.out.println("amount should be greater than 100");
                historyService.addHistory(
                        account.getUsername(),
                        "WITHDRAW", amount, "failed because amount should be greater than 100"
                );
            }else if (withdrawSuccess.getError() == 1) {
                System.out.println("account not found");
                historyService.addHistory(
                        account.getUsername(),
                        "WITHDRAW", amount, "failed because account not found"
                );
            }
    }

    private void deposit(Account account) {
        System.out.println("please enter amount to deposit");
        Scanner scanner  = new Scanner(System.in);
        double amount = scanner.nextDouble();
        AccountResult depositSuccess = accountService.deposit(account, amount);
        if (depositSuccess.getError() == 3) {
            System.out.println("success deposit successfully your balance "+  depositSuccess.getAmount());
            historyService.addHistory(
                    account.getUsername(), "DEPOSIT", amount, "success");

        }else if (depositSuccess.getError() == 2) {
            System.out.println("amount should be greater than 100");
            historyService.addHistory(
                    account.getUsername(),
                    "DEPOSIT", amount, "failed because amount should be greater than 100");
        }else if (depositSuccess.getError() == 1) {
            System.out.println("account not found");
            historyService.addHistory(
                    account.getUsername(), "DEPOSIT", amount, "failed because account not found");
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
            historyService.addHistory(
                    account.getUsername(),
                    "TRANSFER", amount, "failed because the sender account does not exist"
            );

        }
        else if (transferSuccess.getError() == 2) {
            System.out.println("the receiver account does not exist");
            historyService.addHistory(
                    account.getUsername(),
                    "TRANSFER", amount, "failed because the receiver account does not exist"
            );
        }

        else if (transferSuccess.getError() == 3) {
            System.out.println("insufficient funds");
            historyService.addHistory(
                    account.getUsername(),
                    "TRANSFER", amount, "failed due to insufficient funds"
            );
        }
        else if (transferSuccess.getError() == 4) {
            System.out.println("amount should be greater than 100");
            historyService.addHistory(
                    account.getUsername(),
                    "TRANSFER", amount, "failed because  amount should be greater than 100"
            );
        }
        else if (transferSuccess.getError() == 5) {
            System.out.println("transfer successfully your balance "+ transferSuccess.getAmount());
            historyService.addHistory(
                    account.getUsername(), "TRANSFER", amount, "Transferred to " + usernameTo
            );

            historyService.addHistory(
                    usernameTo, "RECEIVE", amount, "Received from " + account.getUsername()
            );
        }
    }

    private void changePassword(Account account ) {
        System.out.println("please enter your current password");
        Scanner scanner = new Scanner(System.in);
        String currentPassword = scanner.next();
        if (!currentPassword.equals(account.getPassword())) {
            System.out.println("wrong current password");
            historyService.addHistory(
                    account.getUsername(),
                    "changePassword", 0, "failed because wrong current password"
            );
            return;
        }
        System.out.println("please enter your new password");
        String newPassword = scanner.next();
        if (currentPassword.equals(newPassword)) {
            System.out.println("same current password");
            historyService.addHistory(
                    account.getUsername(),
                    "changePassword", 0, "failed because same current password "
            );
            return;

        } else if (!accountValidationService.validatePassword(newPassword)) {
            System.out.println("invalid new password format");
            historyService.addHistory(
                    account.getUsername(),
                    "changePassword", 0, "failed because invalid new password format"
            );
            return;
        }
        System.out.println("change password success");
        accountService.changePassword(account, newPassword);
        historyService.addHistory(
                account.getUsername(),
                "changePassword", 0, "success change password"
        );
    }
    private void history(Account account) {

        Map<String, List<History>> histories = historyService.getAllHistories();

        if (histories.isEmpty()) {
            System.out.println("No histories found");
            return;
        }

        histories.forEach(( username, historyList) -> {
            System.out.println("User: " + username);

            if (historyList.isEmpty()) {
                System.out.println("  No history");
            } else {
                for (History h : historyList) {
                    System.out.println(" " + h);
                }
            }
        });
    }
    private void createAdmin(){
        Account account = new Account("Admin" , "Admin@123" , true , true , false );
        accountService.addAccount(account);
    }
    private void adminPanel(Account account) {
        System.out.println("Welcome to Admin Panel");
        System.out.println("services------->");
        System.out.println("please give me your service");
        System.out.println("1.delete account      2.deactivate account");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                System.out.println("delete account");
                deleteAccount(account);
                break;
            case 2:
                System.out.println("deactivate account");
                inactivateAccount(account);
                break;
            default:
                System.out.println("invalid choice");
                break;
        }

    }

    private void inactivateAccount(Account account) {
        System.out.println("Enter username to deactivate");
        Scanner scanner = new Scanner(System.in);
        String username = scanner.next();
        account = accountService.fetchAccountByUserName(username);
        if (account == null) {
            System.out.println("invalid username to deactivate");
        }
        else {
        account.setActive(false);
            System.out.println("account deactivated");
        }

    }

    private void deleteAccount(Account account) {
        System.out.println("Enter username to delete");
        Scanner scanner = new Scanner(System.in);
        String username = scanner.next();
        account = accountService.fetchAccountByUserName(username);
        if (account == null) {
            System.out.println("invalid username to delete");
        }
        else {
            account.setDeleted(true);
            System.out.println("account deleted");
        }

    }
}
