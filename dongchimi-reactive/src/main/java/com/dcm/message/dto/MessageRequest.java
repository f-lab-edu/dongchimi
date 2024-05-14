package com.dcm.message.dto;

public record MessageRequest(Long chatId, String email, String message) {
}
