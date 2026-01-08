package main.ewalletSystem.service.impl;

import main.ewalletSystem.model.History;
import main.ewalletSystem.service.HistoryService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class HistoryServiceImpl implements HistoryService {

    private final Map<String, List<History>> historyMap = new HashMap<>();

    @Override
    public void addHistory(String username, String action, double amount, String status) {
        historyMap
                .computeIfAbsent(username, k -> new ArrayList<>())
                .add(new History( action, amount, status));
    }

    @Override
    public List<History> getAllHistoriesByUsername(String username) {
        return historyMap.getOrDefault(username, new ArrayList<>());
    }

    @Override
    public Map<String, List<History>> getAllHistories() {
        return historyMap;
    }
}