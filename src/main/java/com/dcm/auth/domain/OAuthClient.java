package com.dcm.auth.domain;

import com.dcm.auth.dto.TokenResponse;

public interface OAuthClient {

    String createUri(String redirectUri);
    TokenResponse createGoogleAccessToken(String code);
    OAuth getPlatform();


}
