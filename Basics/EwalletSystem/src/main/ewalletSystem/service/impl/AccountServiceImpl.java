package main.ewalletSystem.service.impl;

import main.ewalletSystem.model.Account;
import main.ewalletSystem.model.EWalletSystem;
import main.ewalletSystem.service.AccountService;

public class AccountServiceImpl  implements AccountService {

    private EWalletSystem eWalletSystem = new EWalletSystem();


    @Override
    public boolean addAccount(Account account) {
        boolean isAccountExist = eWalletSystem.getAccounts().stream()
                .anyMatch(acc -> acc.getUsername().equals(account.getUsername()));
        if (isAccountExist) {
            return false;
        }
        eWalletSystem.getAccounts().add(account);
        return true;
    }

    @Override
    public boolean getAccount(Account account) {
        return eWalletSystem.getAccounts().stream()
                        .anyMatch(acc -> acc.getUsername().equals(account.getUsername()) && acc.getPassword().equals(account.getPassword()));



    }
}
