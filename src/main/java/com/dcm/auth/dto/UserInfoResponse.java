package com.dcm.auth.dto;

import com.dcm.auth.domain.OAuth;

public record UserInfoResponse(String email, String nickname, String picture, OAuth platform) {

    public static UserInfoResponse of(final OAuthGoogleUserInfoResponse user, final OAuth platform) {
        return new UserInfoResponse(user.email(), user.given_name(), user.picture(), platform);
    }

}
