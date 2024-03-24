package com.dcm.job.service;

import com.dcm.job.domain.Job;
import com.dcm.job.domain.repository.JobRepository;
import com.dcm.job.dto.JobRequest;
import com.dcm.job.dto.JobResponse;
import com.dcm.job.exception.NotFoundJobException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class JobService {

    private final JobRepository jobRepository;

    public List<JobResponse> readJobs() {
        List<Job> jobs = jobRepository.findAll();
        return JobResponse.of(jobs);
    }

    @Transactional
    public void writeJobs(JobRequest jobRequest) {
        Job job = Job.of(jobRequest.jobType(), jobRequest.jobName(), jobRequest.useYn());
        jobRepository.save(job);
    }

    @Transactional
    public void deleteJob(Long jobId) {
        validate(jobId);
        jobRepository.deleteById(jobId);
    }

    private void validate(Long jobId) {
        if (!jobRepository.existsById(jobId))
            throw new NotFoundJobException(jobId);
    }

}
