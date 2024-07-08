package com.dcm.message.dto;

import com.dcm.party.domain.PartyLikeUsers;
import com.dcm.party.domain.PartyLikeUsersPK;

public record PartyLikePublishRequest(Long partyId, String memberId) {

    public static PartyLikeUsers toPartyLikeUsers(Long partyId, String memberId) {
        PartyLikeUsersPK pk = new PartyLikeUsersPK(partyId, memberId);
        return PartyLikeUsers.builder()
            .partyLikeUsersIds(pk)
            .build();
    }
}
