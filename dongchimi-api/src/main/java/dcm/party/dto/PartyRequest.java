package dcm.party.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PartyRequest(
        @NotBlank String managerId,
        @NotBlank String partyName,
        @Min(value = 1) @Max(value = 300) Integer capacity,
        String meetAddress,
        String meetAddressName,
        String description,
        @NotNull Long hobbyId) {
}
