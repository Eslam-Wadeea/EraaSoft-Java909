package main.ewalletSystem.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EWalletSystem {
    private final String name = "Eraa soft EWalletSystem";
    List<Account> accounts = new ArrayList<>();
    Map<String, List<History>> historyMap = new HashMap<>();

    public String getName() {
        return name;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public Map<String, List<History>> getHistoryMap() {
        return historyMap;
    }

    public void setHistoryMap(Map<String, List<History>> historyMap) {
        this.historyMap = historyMap;
    }
}
