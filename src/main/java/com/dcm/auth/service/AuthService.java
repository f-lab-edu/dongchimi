package com.dcm.auth.service;

import com.dcm.auth.domain.OAuth;
import com.dcm.auth.dto.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final OAuthClients clients;

    public String createOAuthRedirectUri(final String platform, final String redirectUri) {
        return clients.getRedirectUri(OAuth.from(platform), redirectUri);
    }

    public TokenResponse createOAuthAccessToken(final String platform, final String code) {
        return clients.getToken(OAuth.from(platform), code);
    }

}
