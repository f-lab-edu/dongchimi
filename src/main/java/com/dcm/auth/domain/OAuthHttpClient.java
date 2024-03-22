package com.dcm.auth.domain;

import com.dcm.auth.dto.OAuthGoogleTokenResponse;
import com.dcm.auth.dto.OAuthGoogleUserInfoResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.PostExchange;

public interface OAuthHttpClient {

    @PostExchange(url="https://oauth2.googleapis.com/token")
    OAuthGoogleTokenResponse fetchGoogleToken(@RequestParam HttpEntity<MultiValueMap<String, String>> httpEntity);

    @GetMapping(value = "https://www.googleapis.com/oauth2/v2/userinfo")
    OAuthGoogleUserInfoResponse fetchGoogleUserInfo(@RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken);

}
