package com.dcm.party.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class PartyLikeUsersPK implements Serializable {

    @Column(name = "PARTY_ID")
    private Long partyId;

    @Column(name = "MEMBER_ID")
    private String memberId;

    public PartyLikeUsersPK(Long partyId, String memberId) {
        this.partyId = partyId;
        this.memberId = memberId;
    }

}
