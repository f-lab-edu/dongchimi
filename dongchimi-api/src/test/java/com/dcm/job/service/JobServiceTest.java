package com.dcm.job.service;

import com.dcm.job.exception.NotFoundJobException;
import com.dcm.common.ServiceTest;
import com.dcm.job.domain.Job;
import com.dcm.job.domain.repository.JobRepository;
import com.dcm.job.dto.JobRequest;
import com.dcm.job.dto.JobResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static com.dcm.global.enumurate.YN.Y;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class JobServiceTest extends ServiceTest {

    @Mock
    private JobRepository jobRepository;

    @InjectMocks
    private JobService jobService;

    @DisplayName("성공적으로 업종 목록을 조회한다.")
    @Test
    void successReadJobs() {
        // given
        List<Job> mockJobs = List.of(
                Job.of(1L, "JOB_THEME", "대기업", Y),
                Job.of(2L, "JOB_THEME", "외국계", Y));

        // when
        when(jobRepository.findAll()).thenReturn(mockJobs);
        List<JobResponse> jobs = jobService.readJobs();

        // then
        assertThat(jobs).isNotEmpty();
        assertEquals(mockJobs.size(), jobs.size());
    }

    @DisplayName("성공적으로 업종 항목을 등록한다.")
    @Test
    void successWriteJob() {
        // given
        JobRequest request = new JobRequest("JOB_THEME", "대기업", Y);
        Job job = Job.of(1L, request.jobType(), request.jobName(), request.useYn());


        // when
        doReturn(job).when(jobRepository).save(any(Job.class));
        jobService.writeJobs(request);

        // then
        verify(jobRepository).save(any(Job.class));
    }

    @DisplayName("성공적으로 업종 항목을 삭제한다.")
    @Test
    void successDeleteJob() {
        // given
        Long jobId = 1L;

        // when
        doReturn(true).when(jobRepository).existsById(jobId);
        doNothing().when(jobRepository).deleteById(jobId);
        jobService.deleteJob(jobId);

        // then
        verify(jobRepository).deleteById(jobId);
    }

    @DisplayName("업종 항목이 없는 업종을 삭제하다 예외가 발생한다.")
    @Test
    void notFoundJob() {
        // given
        Long jobId = 1L;

        // when
        doReturn(false).when(jobRepository).existsById(jobId);
        doNothing().when(jobRepository).deleteById(jobId);

        // then
        assertThrows(NotFoundJobException.class, () -> jobService.deleteJob(jobId));
    }

}