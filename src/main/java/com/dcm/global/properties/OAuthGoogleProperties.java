package com.dcm.global.properties;


import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties("oauth.google")
public class OAuthGoogleProperties {

    private final String clientId;
    private final String clientSecret;
    private final String responseType;
    private final List<String> scopes;
    private final String tokenUri;
    private final String endPointUri;
    private final String redirectUri;
    private final String userInfoUri;
    private final String accessType;

    public OAuthGoogleProperties(final String clientId, final String clientSecret, final String responseType, final List<String> scopes,
                                 final String tokenUri, final String endPointUri, final String redirectUri, final String accessType,
                                 final String userInfoUri) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.responseType = responseType;
        this.scopes = scopes;
        this.tokenUri = tokenUri;
        this.endPointUri = endPointUri;
        this.redirectUri = redirectUri;
        this.userInfoUri = userInfoUri;
        this.accessType = accessType;
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public String getResponseType() {
        return responseType;
    }

    public List<String> getScopes() {
        return scopes;
    }

    public String getTokenUri() {
        return tokenUri;
    }

    public String getEndPointUri() {
        return endPointUri;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public String getUserInfoUri() {
        return userInfoUri;
    }

    public String getAccessType() {
        return accessType;
    }
}
