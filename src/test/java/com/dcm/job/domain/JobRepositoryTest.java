package com.dcm.job.domain;

import com.dcm.global.config.JpaAuditingConfiguration;
import com.dcm.job.domain.repository.JobRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
@Import(JpaAuditingConfiguration.class)
public class JobRepositoryTest {

    @Autowired
    private JobRepository jobRepository;

    @DisplayName("성공적으로 업종 목록을 조회한다.")
    @Test
    void successReadJobs() {
        // given
        List<Job> jobs = List.of(
                Job.of("JOB_THEME", "스타트업", "Y"),
                Job.of("JOB_THEME", "강소기업", "Y")
        );
        jobRepository.saveAll(jobs);

        // when
        List<Job> initJobs = jobRepository.findAll();

        // then
        assertThat(initJobs).isNotEmpty();
        assertThat(initJobs).hasSizeGreaterThan(0);
    }

    @DisplayName("성공적으로 업종 목록을 등록한다.")
    @Test
    void successWriteJob() {
        // given
        List<Job> jobs = List.of(
                Job.of("JOB_THEME", "스타트업", "Y"),
                Job.of("JOB_THEME", "강소기업", "Y")
        );
        jobRepository.saveAll(jobs);

        // when
        Job job1 = jobs.get(0);
        Job job2 = jobs.get(1);

        // then
        assertThat(job1.getJobId()).isNotNull();
        assertThat(job2.getJobId()).isNotNull();

    }

    @DisplayName("성공적으로 업종 항목을 삭제한다.")
    @Test
    void successDeleteJob() {
        // given
        Long jobId = 1L;
        List<Job> jobs = List.of(
                Job.of("JOB_THEME", "스타트업", "Y"),
                Job.of("JOB_THEME", "강소기업", "Y")
        );
        jobRepository.saveAll(jobs);

        // when
        boolean exist = jobRepository.existsById(jobId);
        jobRepository.deleteById(jobId);
        boolean empty = jobRepository.existsById(jobId);

        // then
        assertThat(exist).isTrue();
        assertThat(empty).isFalse();

    }

}