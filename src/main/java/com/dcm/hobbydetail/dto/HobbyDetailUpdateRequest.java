package com.dcm.hobbydetail.dto;

public record HobbyDetailUpdateRequest(Long hobbyDetailId, String hobbyDetailName, String useYn, Long hobbyId) {
}
