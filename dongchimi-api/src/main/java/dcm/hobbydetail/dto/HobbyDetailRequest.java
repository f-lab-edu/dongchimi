package dcm.hobbydetail.dto;

import dcm.global.enumurate.YN;

public record HobbyDetailRequest(String hobbyDetailName, YN useYn, Long hobbyId) {
}
