package main.ewalletSystem.service;

import main.ewalletSystem.model.History;
import java.util.List;
import java.util.Map;

public interface HistoryService {

    void addHistory(String username , String action, double amount, String status);
    List<History> getAllHistoriesByUsername(String username);
    Map<String, List<History>> getAllHistories();
}
