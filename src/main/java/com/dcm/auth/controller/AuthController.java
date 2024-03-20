package com.dcm.auth.controller;

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
                                                       @RequestParam final String redirectUri) {
        String platformRedirectUri = authService.createOAuthRedirectUri(platform, redirectUri);
        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.LOCATION, platformRedirectUri)
                .build();
    }

    @GetMapping("/{platform}/login")
    public Object successLogin(@PathVariable final String platform,
                               @RequestParam @NotBlank final String code) {
        return authService.createOAuthAccessToken(platform, code);
    }
}
