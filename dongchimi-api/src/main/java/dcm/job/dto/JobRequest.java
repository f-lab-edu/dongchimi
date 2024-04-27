package dcm.job.dto;

import dcm.global.enumurate.YN;
import jakarta.validation.constraints.NotBlank;

public record JobRequest(
        @NotBlank String jobType,
        @NotBlank String jobName,
        YN useYn
) {
}
