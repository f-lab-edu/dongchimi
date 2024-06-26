package com.dcm.hobby.dto;

import com.dcm.global.enumurate.YN;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record HobbyUpdateRequest(
        @NotNull Long hobbyId,
        @NotEmpty String hobbyName,
        YN useYn
) {}