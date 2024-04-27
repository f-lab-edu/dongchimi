package com.dcm.hobbydetail.dto;

import com.dcm.global.enumurate.YN;

public record HobbyDetailRequest(String hobbyDetailName, YN useYn, Long hobbyId) {
}
