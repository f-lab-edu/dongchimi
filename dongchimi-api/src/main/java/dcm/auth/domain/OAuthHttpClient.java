package dcm.auth.domain;

import dcm.auth.dto.OAuthGoogleTokenResponse;
import dcm.auth.dto.OAuthGoogleUserInfoResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;

public interface OAuthHttpClient {

    @PostExchange(url="https://oauth2.googleapis.com/token")
    OAuthGoogleTokenResponse fetchGoogleToken(@RequestBody MultiValueMap<String, String> params);

    @GetExchange("https://www.googleapis.com/oauth2/v2/userinfo")
    OAuthGoogleUserInfoResponse fetchGoogleUserInfo(@RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken);

}
