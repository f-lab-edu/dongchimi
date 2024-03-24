package com.dcm.auth.domain;

import com.dcm.auth.exception.NotFoundPlatformException;

import java.util.Arrays;

public enum OAuth {

    GOOGLE;

    public static OAuth from(String platform) {
        return Arrays.stream(OAuth.values())
                .filter(oauth -> oauth.name().equalsIgnoreCase(platform))
                .findFirst()
                .orElseThrow(() -> new NotFoundPlatformException(platform));
    }

}
