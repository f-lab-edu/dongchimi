package com.dcm.hobby.exception;

public class NotFoundHobbyException extends RuntimeException {

    public NotFoundHobbyException(Long hobbyId) {
        super(String.format("[Job ID: %s] hobby is not found", hobbyId));
    }

}
