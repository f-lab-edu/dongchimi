package com.dcm.party.context;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Component;

@Component
public class PartyLikeContext {

    private final Map<Long, Long> partyLikeRequestMap;

    public PartyLikeContext() {
        this.partyLikeRequestMap = new ConcurrentHashMap<>();
    }

    public void setPartyLikeRequest(Long partyId) {
        partyLikeRequestMap.merge(partyId, 1L,Long::sum);
    }

    public Map<Long, Long> getAll() {
        return new ConcurrentHashMap<>(partyLikeRequestMap);
    }

    public void clearPartyLikeRequest() {
        partyLikeRequestMap.clear();
    }

}
