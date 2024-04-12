package dcm.auth.service;

import dcm.auth.domain.OAuth;
import dcm.auth.dto.TokenResponse;
import dcm.auth.dto.UserInfoResponse;
import dcm.member.domain.Member;
import dcm.member.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final OAuthClients clients;
    private final MemberRepository memberRepository;

    public String createOAuthRedirectUri(String platform, String redirectUri) {
        return clients.getRedirectUri(OAuth.from(platform), redirectUri);
    }

    @Transactional
    public TokenResponse login(String platform, String code) {
        OAuth oAuth = OAuth.from(platform);
        TokenResponse token = clients.getToken(oAuth, code);
        UserInfoResponse userInfo = clients.getUserInfo(oAuth, token.accessToken(), token.tokenType());

        // user update
        memberRepository.save(Member.of(userInfo));
        return token;
    }

}
