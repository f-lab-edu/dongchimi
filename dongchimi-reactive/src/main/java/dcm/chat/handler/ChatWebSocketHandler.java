package dcm.chat.handler;

import org.springframework.util.ObjectUtils;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

public class ChatWebSocketHandler implements WebSocketHandler {

    private final Map<String, Set<WebSocketSession>> partyChatRooms = new ConcurrentHashMap<>();

    @Override
    public Mono<Void> handle(WebSocketSession session) {
        String partyId = getPartIdFromQuery(session);
        Set<WebSocketSession> chatSession = getChatSession(partyId, session);

        return session.receive()
            .map(WebSocketMessage::getPayloadAsText)
            .flatMap(message -> sendMessageToSession(chatSession, session, message))
            .doFinally(signalType -> removeChatSession(partyId, session))
            .then();
    }

    private Set<WebSocketSession> getChatSession(String partyId, WebSocketSession session) {
        Set<WebSocketSession> sessions = partyChatRooms.computeIfAbsent(partyId, s -> new CopyOnWriteArraySet<>());
        sessions.add(session);
        return sessions;
    }

    private void removeChatSession(String partyId, WebSocketSession session) {
        Set<WebSocketSession> sessions = partyChatRooms.get(partyId);
        if (sessions != null) {
            sessions.remove(session);
            if (sessions.isEmpty()) {
                partyChatRooms.remove(partyId);
            }
        }
    }

    private Mono<Void> sendMessageToSession(Set<WebSocketSession> chatSession, WebSocketSession senderSession, String message) {
        return Mono.when(chatSession.stream()
                .filter(WebSocketSession::isOpen)
                .filter(session -> !session.getId().equals(senderSession.getId()))
                .map(session -> session.send(Mono.just(session.textMessage(message))))
                .toArray(Mono[]::new));
    }

    private String getPartIdFromQuery(WebSocketSession session) {
        String query = session.getHandshakeInfo().getUri().getQuery();
        if (ObjectUtils.isEmpty(query))
            throw new IllegalArgumentException("전달된 파라미터가 없습니다.");
        return query.split("&")[0].split("partyId=")[1];
    }
}
