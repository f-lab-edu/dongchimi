package com.dcm.party.exception;

public class ExistPartyRequestException extends RuntimeException {

    public ExistPartyRequestException(String email) {
        super(String.format("( %s ) 해당 사용자는 이미 신청한 내역이 존재합니다.", email));
    }

}
