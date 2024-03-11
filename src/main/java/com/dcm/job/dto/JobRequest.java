package com.dcm.job.dto;

import jakarta.validation.constraints.NotBlank;

public record JobRequest(
        @NotBlank String jobType,
        @NotBlank String jobName,
        @NotBlank String useYn
) {
}
