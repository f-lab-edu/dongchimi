package com.dcm.message.dto;

public record ChatMessageRequest(Long partyId, Long chatId, String email, String message) {
}
