package com.dcm.session.strategy;

import com.dcm.session.manager.SessionManager;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SessionStrategy {

    private final Map<Boolean, SessionManager> managers;

    public SessionStrategy(Set<SessionManager> managers) {
        Map<Boolean, SessionManager> managerMap = new HashMap<>();
        managers.forEach(manager -> managerMap.put(manager.isSessionActive(), manager));
        this.managers = managerMap;
    }

    public SessionManager getSessionManager() {
        return managers.get(true);
    }
}
