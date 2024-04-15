package dcm.chat.handler;

import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ChatWebSocketHandler implements WebSocketHandler {

    private final Map<String, WebSocketSession> partyChatRooms = new ConcurrentHashMap<>();

    @Override
    public Mono<Void> handle(WebSocketSession session) {
        String partyId = getPartIdFromQuery(session);

        partyChatRooms.put(partyId, session);

        return session.receive()
                .map(WebSocketMessage::getPayloadAsText)
                .flatMap(message -> sendMessageToSession(partyId, message))
                .then()
                .doFinally(signal -> partyChatRooms.remove(partyId));
    }

    private Mono<Void> sendMessageToSession(String partId, String message) {
        WebSocketSession session = partyChatRooms.get(partId);
        if (session != null) {
            return session.send(Mono.just(session.textMessage(message)));
        }
        return Mono.empty();
    }

    private String getPartIdFromQuery(WebSocketSession session) {
        String query = session.getHandshakeInfo().getUri().getQuery();
        return query.split("partyId=")[1];
    }
}
