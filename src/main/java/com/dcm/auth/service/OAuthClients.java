package com.dcm.auth.service;

import com.dcm.auth.domain.OAuth;
import com.dcm.auth.domain.OAuthClient;
import com.dcm.auth.dto.TokenResponse;
import com.dcm.auth.dto.UserInfoResponse;
import com.dcm.auth.exception.NotFoundPlatformException;

import java.util.EnumMap;
import java.util.Map;
import java.util.Set;

public class OAuthClients {

    private final Map<OAuth, OAuthClient> clients;

    public OAuthClients(Set<OAuthClient> clients) {
        EnumMap<OAuth, OAuthClient> clientEnumMap = new EnumMap<>(OAuth.class);
        clients.forEach(client -> clientEnumMap.put(client.getPlatform(), client));
        this.clients = clientEnumMap;
    }

    public String getRedirectUri(OAuth oAuth, String redirectUri) {
        return this.getClient(oAuth).createUri(redirectUri);
    }

    public TokenResponse getToken(OAuth oAuth, String code) {
        return this.getClient(oAuth).createToken(code);
    }

    public UserInfoResponse getUserInfo(OAuth oAuth, String token, String tokenType) {
        String bearerToken = String.format("%s %s", tokenType, token);
        return this.getClient(oAuth).fetchUserInfo(bearerToken);
    }

    private OAuthClient getClient(OAuth oAuth) {
        OAuthClient oAuthClient = clients.get(oAuth);
        if (oAuthClient == null)
            throw new NotFoundPlatformException(oAuth.name());

        return oAuthClient;
    }

}
