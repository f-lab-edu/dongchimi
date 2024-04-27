package dcm.auth.dto;

public record OAuthGoogleTokenResponse(String access_token, String expires_in, String refresh_token, String scope,
                                       String token_type) {
}
