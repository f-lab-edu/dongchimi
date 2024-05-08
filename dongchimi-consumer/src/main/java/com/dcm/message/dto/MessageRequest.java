package com.dcm.message.dto;

public record MessageRequest(Long partyId, Long chatId, String email, String message) {
}
