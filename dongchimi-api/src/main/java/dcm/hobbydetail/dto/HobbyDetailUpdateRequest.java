package dcm.hobbydetail.dto;

import dcm.global.enumurate.YN;

public record HobbyDetailUpdateRequest(Long hobbyDetailId, String hobbyDetailName, YN useYn, Long hobbyId) {
}
