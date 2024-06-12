package com.dcm.party.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PartyJoinRequest(
    @NotNull Long partyId,
    @NotBlank String email,
    @NotBlank String requestReason) {
}
