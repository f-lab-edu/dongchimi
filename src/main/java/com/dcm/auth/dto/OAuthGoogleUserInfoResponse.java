package com.dcm.auth.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(value = SnakeCaseStrategy.class)
public record OAuthGoogleUserInfoResponse(String id, String email, String name, String givenName,
        String picture, String locale) {
}
