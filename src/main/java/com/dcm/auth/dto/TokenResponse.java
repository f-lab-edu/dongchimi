package com.dcm.auth.dto;

public record TokenResponse(String accessToken, String refreshToken) {
}
