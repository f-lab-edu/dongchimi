package com.dcm.auth.service;

import com.dcm.auth.domain.OAuth;
import com.dcm.auth.dto.TokenResponse;
import com.dcm.auth.dto.UserInfoResponse;
import com.dcm.user.domain.User;
import com.dcm.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final OAuthClients clients;
    private final UserRepository userRepository;

    public String createOAuthRedirectUri(final String platform, final String redirectUri) {
        return clients.getRedirectUri(OAuth.from(platform), redirectUri);
    }

    @Transactional
    public TokenResponse login(final String platform, final String code) {
        OAuth oAuth = OAuth.from(platform);
        TokenResponse token = clients.getToken(oAuth, code);
        UserInfoResponse userInfo = clients.getUserInfo(oAuth, token.accessToken(), token.tokenType());

        // user update
        userRepository.save(User.of(userInfo));
        return token;
    }

}
