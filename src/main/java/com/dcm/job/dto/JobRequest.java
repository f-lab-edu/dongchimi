package com.dcm.job.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record JobRequest(
        @NotBlank String jobType,
        @NotBlank String jobName,
        @NotBlank @Pattern(regexp = "^[YN]$", message = "useYn must be 'Y' or 'N'") String useYn
) {
}
