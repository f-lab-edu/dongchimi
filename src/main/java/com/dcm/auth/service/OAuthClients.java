package com.dcm.auth.service;

import com.dcm.auth.domain.OAuth;
import com.dcm.auth.domain.OAuthClient;
import com.dcm.auth.dto.TokenResponse;
import com.dcm.auth.exception.NotFoundPlatformException;

import java.util.EnumMap;
import java.util.Map;
import java.util.Set;

public class OAuthClients {

    private final Map<OAuth, OAuthClient> clients;

    public OAuthClients(final Set<OAuthClient> clients) {
        EnumMap<OAuth, OAuthClient> clientEnumMap = new EnumMap<>(OAuth.class);
        clients.forEach(client -> clientEnumMap.put(client.getPlatform(), client));
        this.clients = clientEnumMap;
    }

    public String getRedirectUri(final OAuth oAuth, final String redirectUri) {
        return this.getClient(oAuth).createUri(redirectUri);
    }

    public TokenResponse getToken(final OAuth oAuth, final String code) {
        return this.getClient(oAuth).createGoogleAccessToken(code);
    }

    private OAuthClient getClient(final OAuth oAuth) {
        OAuthClient oAuthClient = clients.get(oAuth);
        if (oAuthClient == null)
            throw new NotFoundPlatformException(oAuth.name());

        return oAuthClient;
    }

}
