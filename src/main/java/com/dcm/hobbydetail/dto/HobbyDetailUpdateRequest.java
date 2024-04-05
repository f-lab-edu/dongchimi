package com.dcm.hobbydetail.dto;

import com.dcm.global.enumurate.YN;

public record HobbyDetailUpdateRequest(Long hobbyDetailId, String hobbyDetailName, YN useYn, Long hobbyId) {
}
