package com.example.lpgmanagement.services.access;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AccessControlService {
    private Map<String, Boolean> tableEditStatus = new HashMap<>();

    public void setTableEditStatus(String tableName, boolean isEditable) {
        tableEditStatus.put(tableName, isEditable);
    }

    public boolean isTableEditable(String tableName) {
        return tableEditStatus.getOrDefault(tableName, false);
    }
}