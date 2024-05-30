package com.dcm.global.stomp;

import java.security.Principal;


public record StompPrincipal(String name) implements Principal {

    public StompPrincipal(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
