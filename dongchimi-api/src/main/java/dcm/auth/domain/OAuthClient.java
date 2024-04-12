package dcm.auth.domain;

import dcm.auth.dto.TokenResponse;
import dcm.auth.dto.UserInfoResponse;

public interface OAuthClient {

    String createUri(String redirectUri);
    TokenResponse createToken(String code);
    UserInfoResponse fetchUserInfo(String token);
    OAuth getPlatform();


}
