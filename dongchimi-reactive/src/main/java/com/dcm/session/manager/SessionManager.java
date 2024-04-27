package com.dcm.session.manager;

import org.springframework.web.reactive.socket.WebSocketSession;

import java.util.Set;

public interface SessionManager {
    Set<WebSocketSession> getSession(String targetId, WebSocketSession session);
    void removeSession(String targetId, WebSocketSession session);
    boolean isSessionActive();
}
