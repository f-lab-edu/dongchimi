package com.dcm.party.exception;

public class NotFoundPartyException extends RuntimeException {

    public NotFoundPartyException(Long partyId) {
        super(String.format("[Party ID: %s] party is not found", partyId));
    }

}
