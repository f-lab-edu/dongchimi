package com.dcm.job.domain;

import com.dcm.global.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
@Builder
@Table(name = "JOB")
public class Job extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jobId;

    @Column(nullable = false)
    private String jobType;

    @Column(nullable = false)
    private String jobName;

    @Column(nullable = false)
    private String useYn;

    protected Job() {}

    public Job(final String jobType, final String jobName, final String useYn) {
        this.jobType = jobType;
        this.jobName = jobName;
        this.useYn = useYn;
    }

    public Job(final Long jobId, final String jobType, final String jobName, final String useYn) {
        this.jobId = jobId;
        this.jobType = jobType;
        this.jobName = jobName;
        this.useYn = useYn;
    }


}
