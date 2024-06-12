package com.dcm.hobby.dto;

import com.dcm.global.enumurate.YN;
import jakarta.validation.constraints.NotBlank;

public record HobbyRequest(
        @NotBlank String hobbyName,
        YN useYn
) {}