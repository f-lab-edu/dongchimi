package com.dcm.auth.controller;

import com.dcm.auth.dto.TokenResponse;
import com.dcm.auth.service.AuthService;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auth")
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/{platform}/uri")
    public ResponseEntity<Void> createOAuthRedirectUri(@PathVariable final String platform,
                                                       @RequestParam @NotBlank final String redirectUri) {
        String platformRedirectUri = authService.createOAuthRedirectUri(platform, redirectUri);
        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.LOCATION, platformRedirectUri)
                .build();
    }

    @GetMapping("/{platform}/login")
    public ResponseEntity<Void> login(@PathVariable final String platform,
                                      @RequestParam @NotBlank final String code) {
        TokenResponse token = authService.login(platform, code);
        return ResponseEntity.status(HttpStatus.OK)
                .header("access-token", token.accessToken())
                .header("refresh-token", token.refreshToken())
                .build();
    }
}
