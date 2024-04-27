package com.dcm.auth.domain;

import com.dcm.auth.dto.TokenResponse;
import com.dcm.auth.dto.UserInfoResponse;

public interface OAuthClient {

    String createUri(String redirectUri);
    TokenResponse createToken(String code);
    UserInfoResponse fetchUserInfo(String token);
    OAuth getPlatform();


}
