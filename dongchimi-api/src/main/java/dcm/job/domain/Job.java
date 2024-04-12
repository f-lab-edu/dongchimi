package dcm.job.domain;

import dcm.global.domain.BaseEntity;
import dcm.global.enumurate.YN;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Entity
@Getter
@AllArgsConstructor
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
    private YN useYn;

    protected Job() {}

    public static Job of(String jobType, String jobName, YN useYn) {
        return of(null, jobType, jobName, useYn);
    }

    public static Job of(Long jobId, String jobType, String jobName, YN useYn) {
        return new Job(jobId, jobType, jobName, useYn);
    }


}
