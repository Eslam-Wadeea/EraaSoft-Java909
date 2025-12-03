package main.ewalletSystem.service;

import main.ewalletSystem.model.Account;

public interface AccountService {
    boolean addAccount(Account account);
    boolean getAccount(Account account);

}
