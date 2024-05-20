package com.dcm.global.exception;

public record ErrorMessage<T>(String message, T object) {}
