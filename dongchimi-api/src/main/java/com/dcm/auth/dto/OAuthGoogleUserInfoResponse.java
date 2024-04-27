package com.dcm.auth.dto;

public record OAuthGoogleUserInfoResponse(String id, String email, String name, String given_name,
        String picture, String locale) {
}
