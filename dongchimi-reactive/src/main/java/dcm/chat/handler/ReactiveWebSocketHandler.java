package dcm.chat.handler;

import dcm.global.utils.WebUtils;
import dcm.session.manager.SessionManager;
import dcm.session.strategy.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Mono;

import java.util.Set;

@Component
public class ReactiveWebSocketHandler implements WebSocketHandler {

    private final SessionManager sessionManager;

    public ReactiveWebSocketHandler(SessionStrategy sessionStrategy) {
        this.sessionManager = sessionStrategy.getSessionManager();
    }

    @Override
    public Mono<Void> handle(WebSocketSession session) {
        String partyId = WebUtils.getQueryParam(session.getHandshakeInfo().getUri().toString(), "partyId");
        Set<WebSocketSession> chatSession = sessionManager.getSession(partyId, session);

        return session.receive()
            .map(WebSocketMessage::getPayloadAsText)
            .flatMap(message -> sendMessageToSession(chatSession, session, message))
            .doFinally(signalType -> sessionManager.removeSession(partyId, session))
            .then();
    }

    public Mono<Void> sendMessageToSession(Set<WebSocketSession> chatSession, WebSocketSession senderSession, String message) {
        return Mono.when(chatSession.stream()
                .filter(WebSocketSession::isOpen)
                .filter(session -> !session.getId().equals(senderSession.getId()))
                .map(session -> session.send(Mono.just(session.textMessage(message))))
                .toArray(Mono[]::new));
    }

}
