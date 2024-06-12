package com.dcm.hobbydetail.dto;

import com.dcm.global.enumurate.YN;
import com.dcm.hobbydetail.domain.HobbyDetail;
import java.util.List;
import java.util.stream.Collectors;

public record HobbyDetailResponse(Long hobbyDetailId, String hobbyDetailName, YN useYn) {

    public static HobbyDetailResponse of(HobbyDetail hobbyDetail) {
        return new HobbyDetailResponse(hobbyDetail.getHobbyDetailId(), hobbyDetail.getHobbyDetailName(), hobbyDetail.getUseYn());
    }

    public static List<HobbyDetailResponse> of(List<HobbyDetail> hobbyDetailList) {
        return hobbyDetailList.stream()
                .map(HobbyDetailResponse::of)
                .collect(Collectors.toList());
    }
}
