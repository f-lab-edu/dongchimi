package com.dcm.auth.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(value = SnakeCaseStrategy.class)
public record OAuthGoogleTokenResponse(String accessToken, String expiresIn, String refreshToken, String scope,
                                       String tokenType) {
}
