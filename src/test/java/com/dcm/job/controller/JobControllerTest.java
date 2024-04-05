package com.dcm.job.controller;

import com.dcm.common.ControllerTest;
import com.dcm.job.dto.JobRequest;
import com.dcm.job.dto.JobResponse;
import com.dcm.job.service.JobService;
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

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(JobController.class)
class JobControllerTest extends ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JobService jobService;

    @DisplayName("성공적으로 업종 목록을 조회한다.")
    @Test
    void successReadJobs() throws Exception {
        // given
        List<JobResponse> jobs = List.of(
                new JobResponse(1L, "JOB_THEME", "대기업", LocalDateTime.now(), LocalDateTime.now()),
                new JobResponse(2L, "JOB_THEME", "외국계", LocalDateTime.now(), LocalDateTime.now()));
        given(jobService.readJobs()).willReturn(jobs);

        // when & then
        mockMvc.perform(get("/api/job"))
                .andDo(print())
                .andDo(document("read-jobs",
                        preprocessRequest(prettyPrint()),
                        Preprocessors.preprocessResponse(prettyPrint()),
                        responseFields(
                                fieldWithPath("[].jobId").type(JsonFieldType.NUMBER).description("업종 ID"),
                                fieldWithPath("[].jobType").type(JsonFieldType.STRING).description("업종 구분"),
                                fieldWithPath("[].jobName").type(JsonFieldType.STRING).description("업종 명"),
                                fieldWithPath("[].createDate").type(JsonFieldType.STRING).description("생성일자").attributes(key("format").value("yyyy-MM-dd'T'HH:mm:ss")),
                                fieldWithPath("[].updateDate").type(JsonFieldType.STRING).description("수정일자").attributes(key("format").value("yyyy-MM-dd'T'HH:mm:ss"))
                        )))
                .andExpect(status().isOk());
    }

    @DisplayName("성공적으로 업종 항목을 등록한다.")
    @Test
    void successWriteJob() throws Exception {
        // given
        JobRequest request = new JobRequest("JOB_THEME", "대기업", "Y");

        // when
        mockMvc.perform(post("/api/job")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isOk());

        // then
        verify(jobService).writeJobs(any(JobRequest.class));
    }

    @DisplayName("성공적으로 업종 항목을 제거한다.")
    @Test
    void successDeleteJob() throws Exception {
        // given
        Long jobId = 1L;

        // when
        mockMvc.perform(delete("/api/job/{jobId}", jobId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // then
        verify(jobService).deleteJob(jobId);
    }

}