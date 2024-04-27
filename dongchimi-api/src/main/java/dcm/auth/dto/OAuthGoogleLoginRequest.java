package dcm.auth.dto;

public record OAuthGoogleLoginRequest(String clientId, String redirectUri, String clientSecret, String responseType, String scope,
                                      String code, String accessType, String grantType, String state,
                                      String includeGrantedScopes, String loginHint, String prompt) {
}
