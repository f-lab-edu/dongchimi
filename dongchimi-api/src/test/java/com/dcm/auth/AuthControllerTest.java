package com.dcm.auth;

import com.dcm.auth.controller.AuthController;
import com.dcm.auth.dto.TokenResponse;
import com.dcm.auth.service.AuthService;
import com.dcm.common.ControllerTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthController.class)
public class AuthControllerTest extends ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthService authService;


    @DisplayName("OAuth2 구글 로그인 할 수 있는 링크를 반환한다.")
    @Test
    void successCreateOAuthRedirectUri() throws Exception {
        // given
        String callbackRedirectUri = "http://localhost:8080/auth/google/login";
        String platformRedirectUri = String.format("https://accounts.google.com/o/oauth2/v2/auth" +
                "?client_id=1234" +
                "&redirect_uri=%s" +
                "&response_type=code" +
                "&scope=https://www.googleapis.com/auth/userinfo.profile https://www.googleapis.com/auth/userinfo.email" +
                "&access_type=offline" +
                "&prompt=consent", callbackRedirectUri);

        given(authService.createOAuthRedirectUri(any(), any())).willReturn(platformRedirectUri);

        // when
        mockMvc.perform(get("/auth/{platform}/uri", "google")
                        .param("redirectUri", callbackRedirectUri))
                .andDo(print())
                .andDo(document("create-oauth-redirect-uri",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(
                                parameterWithName("platform").description("OAuth 플랫폼 명")
                        ),
                        queryParameters(
                                parameterWithName("redirectUri").description("로그인 인증 후 Callback URI")
                        )
                ))
                .andExpect(header().string(HttpHeaders.LOCATION, platformRedirectUri))
                .andExpect(status().isOk());
    }

    @DisplayName("OAuth2로 로그인한 사용자에게 토큰 정보를 반환한다.")
    @Test
    void successLogin() throws Exception {
        // given
        String code = "google-authentication-code";
        TokenResponse tokenResponse = new TokenResponse("accessToken", "refreshToken", "Bearer");
        given(authService.login(any(), any())).willReturn(tokenResponse);

        // when
        mockMvc.perform(get("/auth/{platform}/login", "google")
                .param("code", code))
                .andDo(print())
                .andDo(document("oauth2-login",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(
                                parameterWithName("platform").description("OAuth 플랫폼 명")
                        ),
                        queryParameters(
                                parameterWithName("code").description("OAuth2 인증 코드")
                        ),
                        responseFields(
                                fieldWithPath("accessToken").type(JsonFieldType.STRING).description("Access Token"),
                                fieldWithPath("refreshToken").type(JsonFieldType.STRING).description("Refresh Token"),
                                fieldWithPath("tokenType").type(JsonFieldType.STRING).description("Bearer")
                        )))
                .andExpect(status().isOk());
    }

}
