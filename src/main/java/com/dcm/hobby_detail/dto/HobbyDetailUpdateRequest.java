package com.dcm.hobby_detail.dto;

public record HobbyDetailUpdateRequest(Long hobbyDetailId, String hobbyDetailName, String useYn, Long hobbyId) {
}
