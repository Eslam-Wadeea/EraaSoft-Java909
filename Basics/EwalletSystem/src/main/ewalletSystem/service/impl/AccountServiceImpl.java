package main.ewalletSystem.service.impl;

import main.ewalletSystem.helper.AccountResult;
import main.ewalletSystem.model.Account;
import main.ewalletSystem.model.EWalletSystem;
import main.ewalletSystem.service.AccountService;

import java.util.Optional;

public class AccountServiceImpl  implements AccountService {

    private final EWalletSystem eWalletSystem = new EWalletSystem();

    @Override
    public boolean addAccount(Account account) {
        Optional<Account>optionalAccount = getOptionalAccountByUserName(account);
        if (optionalAccount.isPresent()) {
            return false;
        }
        eWalletSystem.getAccounts().add(account);
        return true;
    }

    @Override
    public boolean getAccountByUserNameAndPassword(Account account) {
        return eWalletSystem.getAccounts().stream()
                        .anyMatch(acc -> acc.getUsername().equals(account.getUsername())
                                && acc.getPassword().equals(account.getPassword()));
    }

    @Override
    public Account getAccountByUserName(Account account) {
        Optional<Account>optionalAccount = getOptionalAccountByUserName(account);
        if (optionalAccount.isEmpty()) {
            return null;
        }else {
            return optionalAccount.get();
        }
    }

    @Override
    public AccountResult withdraw(Account account, double amount) {
        Optional<Account>optionalAccount = getOptionalAccountByUserName(account);
        if (optionalAccount.isEmpty()) {
            return new AccountResult(1);
        }

        if (amount < 100) {
            return new AccountResult(2);
        }
        Account accountToWithdraw = optionalAccount.get();
        if (accountToWithdraw.getBalance() < amount) {
            return new AccountResult(3);
        }
        accountToWithdraw.setBalance(accountToWithdraw.getBalance() - amount);
        return new AccountResult(4 ,  accountToWithdraw.getBalance());

    }
    @Override
    public AccountResult deposit(Account account, double amount) {
        Optional<Account>optionalAccount = getOptionalAccountByUserName(account);
        if (optionalAccount.isEmpty()) {
            return new AccountResult(1);
        }

        if (amount < 100) {
            return new AccountResult(2);
        }

        Account accountToDeposit = optionalAccount.get();
        accountToDeposit.setBalance(accountToDeposit.getBalance() + amount);
        return new AccountResult(3 , accountToDeposit.getBalance());
    }

    @Override
    public AccountResult transferMoney(Account account ,Account accountTo ,  double amount) {
        Optional<Account>optionalAccountFrom = getOptionalAccountByUserName(account);
        if (optionalAccountFrom.isEmpty()) {
            return new AccountResult(1);
        }
        Optional<Account> optionalAccountTo = getOptionalAccountByUserName(accountTo);
        if (optionalAccountTo.isEmpty()) {
            return new AccountResult(2);
        }
        if (amount > account.getBalance()) {
            return new AccountResult(3);
        }
        if(amount < 100){
            return new AccountResult(4);
        }
        account.setBalance(account.getBalance() - amount);
        accountTo.setBalance(accountTo.getBalance() + amount);
        return new AccountResult(5 ,account.getBalance());
    }

    @Override
    public void changePassword(Account account , String  newPassword) {
        Optional<Account> optionalAccount =eWalletSystem.getAccounts().stream()
                .filter(acc -> acc.getUsername().equals(account.getUsername())).findFirst();
        if (optionalAccount.isPresent()) {
            Account accountToUpdatePassword = optionalAccount.get();
            accountToUpdatePassword.setPassword(newPassword);
        }

    }

    @Override
    public Account fetchAccountByUserName(String username) {
        return eWalletSystem.getAccounts().stream()
                .filter(acc -> acc.getUsername().equals(username))
                .findFirst().orElse(null);
    }

    private Optional<Account> getOptionalAccountByUserName(Account account) {
        Optional<Account> optionalAccount =eWalletSystem.getAccounts().stream()
                .filter(acc -> acc.getUsername().equals(account.getUsername())).findFirst();
        return optionalAccount;
    }

}
