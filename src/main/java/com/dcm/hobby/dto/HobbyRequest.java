package com.dcm.hobby.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record HobbyRequest(
        @NotBlank String hobbyName,
        @NotBlank @Pattern(regexp = "^[YN]$", message = "useYn must be 'Y' or 'N'") String useYn
) {}