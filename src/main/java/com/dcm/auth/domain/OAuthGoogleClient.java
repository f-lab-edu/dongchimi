package com.dcm.auth.domain;

import com.dcm.auth.dto.OAuthGoogleAccessResponse;
import com.dcm.auth.dto.TokenResponse;
import com.dcm.global.properties.OAuthGoogleProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
@Slf4j
public class OAuthGoogleClient implements OAuthClient {

    private final OAuthGoogleProperties properties;
    private final RestTemplate restTemplate;

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
    public TokenResponse createGoogleAccessToken(final String code) {
        // 사용자 인증 API 호출
        MultiValueMap<String, String> params = this.createOAuthGoogleParams(code, properties.getRedirectUri());
        HttpEntity<MultiValueMap<String, String>> httpEntity = this.createHttpEntity(params);
        OAuthGoogleAccessResponse googleAccessToken = fetchGoogleToken(httpEntity);
        return new TokenResponse(googleAccessToken.access_token(), googleAccessToken.refresh_token());
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

    private HttpEntity<MultiValueMap<String, String>> createHttpEntity(MultiValueMap<String, String> params) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        return new HttpEntity<>(params, headers);
    }

    private OAuthGoogleAccessResponse fetchGoogleToken(HttpEntity<MultiValueMap<String, String>> httpEntity) {
        try {
            return restTemplate.postForEntity(properties.getTokenUri(), httpEntity, OAuthGoogleAccessResponse.class).getBody();
        } catch (Exception e) {
            log.warn(e.getMessage());
            throw new RuntimeException("구글 로그인에 접근을 허용하지 않았습니다.");
        }
    }

    @Override
    public OAuth getPlatform() {
        return OAuth.GOOGLE;
    }
}
