package dcm.job.dto;

import dcm.job.domain.Job;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record JobResponse(
        Long jobId,
        String jobType,
        String jobName,
        LocalDateTime createDate,
        LocalDateTime updateDate
) {
    public static JobResponse of(Job job) {
        return new JobResponse(job.getJobId(), job.getJobType(), job.getJobName(), job.getCreateDate(),
                job.getUpdateDate());
    }

    public static List<JobResponse> of(List<Job> jobs) {
        return jobs.stream()
                .map(JobResponse::of)
                .collect(Collectors.toList());
    }
}
