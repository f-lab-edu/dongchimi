package com.dcm.hobby.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record HobbyUpdateRequest(
        @NotNull Long hobbyId,
        @NotEmpty String hobbyName,
        @NotEmpty @Pattern(regexp = "^[YN]$", message = "useYn must be 'Y' or 'N'") String useYn
) {}