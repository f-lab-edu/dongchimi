package com.dcm.hobby.dto;

import com.dcm.hobby.domain.Hobby;

import java.util.List;
import java.util.stream.Collectors;

public record HobbyResponse(Long hobbyId, String hobbyName, String useYn) {

    public static HobbyResponse of (Hobby hobby) {
        return new HobbyResponse(hobby.getHobbyId(), hobby.getHobbyName(), hobby.getUseYn());
    }


    public static List<HobbyResponse> of (List<Hobby> hobbyList) {
        return hobbyList.stream()
                .map(HobbyResponse::of)
                .collect(Collectors.toList());
    }
}
