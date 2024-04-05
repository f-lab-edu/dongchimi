package com.dcm.hobby.dto;

import com.dcm.hobby.domain.Hobby;
import com.dcm.hobbydetail.dto.HobbyDetailResponse;

import java.util.List;
import java.util.stream.Collectors;

public record HobbyResponse(Long hobbyId, String hobbyName, String useYn, List<HobbyDetailResponse> hobbyDetails) {

    public static HobbyResponse of(Hobby hobby) {
        List<HobbyDetailResponse> hobbyDetailResponses = hobby.getHobbyDetails().stream()
                .map(HobbyDetailResponse::of)
                .collect(Collectors.toList());
        return new HobbyResponse(hobby.getHobbyId(), hobby.getHobbyName(), hobby.getUseYn(), hobbyDetailResponses);
    }


    public static List<HobbyResponse> of(List<Hobby> hobbyList) {
        return hobbyList.stream()
                .map(HobbyResponse::of)
                .collect(Collectors.toList());
    }
}
