package com.dcm.hobby.controller;

import com.dcm.common.ControllerTest;
import com.dcm.hobby.dto.HobbyRequest;
import com.dcm.hobby.dto.HobbyResponse;
import com.dcm.hobby.dto.HobbyUpdateRequest;
import com.dcm.hobby.service.HobbyService;
import com.dcm.hobbydetail.dto.HobbyDetailResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(HobbyController.class)
class HobbyControllerTest extends ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HobbyService hobbyService;

    @DisplayName("성공적으로 취미 목록을 조회한다.")
    @Test
    void successReadHobby() throws Exception {
        // given
        List<HobbyDetailResponse> hobbyDetailResponses = List.of(
                new HobbyDetailResponse(1L, "자전거", "Y"),
                new HobbyDetailResponse(2L, "배드민턴", "Y")
        );
        List<HobbyResponse> hobbyResponses = List.of(new HobbyResponse(1L, "운동/스포츠", "Y", hobbyDetailResponses));
        given(hobbyService.readHobbyList()).willReturn(hobbyResponses);

        // when & then
        mockMvc.perform(get("/api/hobby"))
                .andDo(print())
                .andDo(document("read-hobby",
                        preprocessRequest(prettyPrint()),
                        Preprocessors.preprocessResponse(prettyPrint()),
                        responseFields(
                                fieldWithPath("[].hobbyId").type(JsonFieldType.NUMBER).description("취미 ID"),
                                fieldWithPath("[].hobbyName").type(JsonFieldType.STRING).description("취미 명"),
                                fieldWithPath("[].useYn").type(JsonFieldType.STRING).description("취미 사용여부"),
                                fieldWithPath("[].hobbyDetails").type(JsonFieldType.ARRAY).description("취미상세 목록"),
                                fieldWithPath("[].hobbyDetails[].hobbyDetailId").type(JsonFieldType.NUMBER).description("취미상세 ID"),
                                fieldWithPath("[].hobbyDetails[].hobbyDetailName").type(JsonFieldType.STRING).description("취미상세 명"),
                                fieldWithPath("[].hobbyDetails[].useYn").type(JsonFieldType.STRING).description("취미상세 사용여부")
                        )))
                .andExpect(status().isOk());
    }

    @DisplayName("성공적으로 취미 항목을 등록한다.")
    @Test
    void successWriteJob() throws Exception {
        // given
        HobbyRequest request = new HobbyRequest("운동/스포츠", "Y");

        // when
        mockMvc.perform(post("/api/hobby")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isCreated());

        // then
        verify(hobbyService).writeHobby(any(HobbyRequest.class));
    }

    @DisplayName("성공적으로 취미를 업데이트한다.")
    @Test
    void successUpdateHobby() throws Exception {
        // Given
        HobbyUpdateRequest request = new HobbyUpdateRequest(1L, "자전거", "N");
        doNothing().when(hobbyService).updateHobby(any(HobbyUpdateRequest.class));

        // When & Then
        mockMvc.perform(patch("/api/hobby")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andDo(print())
                .andDo(document("update-hobby",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                fieldWithPath("hobbyId").type(JsonFieldType.NUMBER).description("취미 ID"),
                                fieldWithPath("hobbyName").type(JsonFieldType.STRING).description("취미 명"),
                                fieldWithPath("useYn").type(JsonFieldType.STRING).description("취미 사용여부")
                        )))
                .andExpect(status().isCreated());
    }

    @DisplayName("성공적으로 취미 항목을 제거한다.")
    @Test
    void successDeleteJob() throws Exception {
        // given
        Long hobbyId = 1L;

        // when
        mockMvc.perform(delete("/api/hobby/{hobbyId}", hobbyId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        // then
        verify(hobbyService).deleteHobby(hobbyId);
    }

}