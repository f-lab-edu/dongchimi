package dcm.session.manager;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketSession;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
public class ReactiveSessionManager implements SessionManager {

    private final Map<String, Set<WebSocketSession>> sessionStorage = new ConcurrentHashMap<>();

    @Value("${session.manager.reactive}")
    private boolean isSessionActive;

    @Override
    public Set<WebSocketSession> getSession(String targetId, WebSocketSession session) {
        Set<WebSocketSession> sessions = sessionStorage.computeIfAbsent(targetId, s -> new CopyOnWriteArraySet<>());
        sessions.add(session);
        return sessions;
    }

    @Override
    public void removeSession(String targetId, WebSocketSession session) {
        Set<WebSocketSession> sessions = sessionStorage.get(targetId);
        if (sessions == null) return;

        sessions.remove(session);
        if (sessions.isEmpty()) {
            sessionStorage.remove(targetId);
        }
    }

    @Override
    public boolean isSessionActive() {
        return isSessionActive;
    }

}
