package com.dcm.auth.dto;

public record OAuthGoogleAccessResponse(String access_token, String expires_in, String refresh_token, String scope,
                                        String token_type) {
}
