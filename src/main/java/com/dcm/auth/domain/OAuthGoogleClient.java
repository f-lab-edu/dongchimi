package com.dcm.auth.domain;

import com.dcm.auth.dto.OAuthGoogleTokenResponse;
import com.dcm.auth.dto.OAuthGoogleUserInfoResponse;
import com.dcm.auth.dto.TokenResponse;
import com.dcm.auth.dto.UserInfoResponse;
import com.dcm.global.properties.OAuthGoogleProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Component
@RequiredArgsConstructor
@Slf4j
public class OAuthGoogleClient implements OAuthClient {

    private final OAuthGoogleProperties properties;
    private final OAuthHttpClient oAuthHttpClient;

    @Override
    public String createUri(final String redirectUri) {
        return properties.getEndPointUri() + "?" +
                "client_id=" + properties.getClientId() +
                "&redirect_uri=" + redirectUri +
                "&response_type=code" +
                "&scope=" + String.join(" ", properties.getScopes()) +
                "&access_type=" + properties.getAccessType() +
                "&prompt=consent";
    }

    @Override
    public TokenResponse createToken(final String code) {
        MultiValueMap<String, String> params = this.createOAuthGoogleParams(code, properties.getRedirectUri());
        OAuthGoogleTokenResponse googleAccessToken = oAuthHttpClient.fetchGoogleToken(params);
        return new TokenResponse(googleAccessToken.access_token(), googleAccessToken.refresh_token(), googleAccessToken.token_type());
    }

    @Override
    public UserInfoResponse fetchUserInfo(final String token) {
        OAuthGoogleUserInfoResponse oAuthGoogleUserInfoResponse = oAuthHttpClient.fetchGoogleUserInfo(token);
        return  UserInfoResponse.of(oAuthGoogleUserInfoResponse, OAuth.GOOGLE);
    }

    private MultiValueMap<String, String> createOAuthGoogleParams(final String code, final String redirectUri) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("client_id", properties.getClientId());
        params.add("client_secret", properties.getClientSecret());
        params.add("code", code);
        params.add("grant_type", "authorization_code");
        params.add("redirect_uri", redirectUri);
        return params;
    }

    @Override
    public OAuth getPlatform() {
        return OAuth.GOOGLE;
    }
}
