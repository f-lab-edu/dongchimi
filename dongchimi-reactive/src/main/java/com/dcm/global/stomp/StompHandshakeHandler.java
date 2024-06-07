package com.dcm.global.stomp;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;
import java.util.UUID;

public class StompHandshakeHandler extends DefaultHandshakeHandler {

    /**
     * Websocket의 사용자를 식별을 결정하기 위해 재정의 할 수 있는 메서드. 이 메서드를 재정의하는 것은 Websocket을 연결할 때
     * 사용자 식별자인 Principla을 제공받는데, 이 식별자를 UUID로 지정하여 Websocket 연결 시 사용자에게 부여할 수 있도록 정의.
     */
    @Override
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
        return new StompPrincipal(UUID.randomUUID().toString());
    }
}
