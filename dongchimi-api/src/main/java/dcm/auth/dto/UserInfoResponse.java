package dcm.auth.dto;

import dcm.auth.domain.OAuth;

public record UserInfoResponse(String email, String nickname, String picture, OAuth platform) {

    public static UserInfoResponse of(OAuthGoogleUserInfoResponse user, OAuth platform) {
        return new UserInfoResponse(user.email(), user.given_name(), user.picture(), platform);
    }

}
