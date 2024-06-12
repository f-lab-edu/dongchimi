package com.dcm.party.domain;

import com.dcm.global.domain.BaseEntity;
import com.dcm.party.dto.PartyJoinRequest;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class PartyJoinUser extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private PartyJoinStatus partyJoinStatus;

    private String requestReason;

    private String rejectReason;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "partyId")
    private Party party;

    public static PartyJoinUser toPartyJoin(PartyJoinRequest request, Party party) {
        return PartyJoinUser.builder()
            .email(request.email())
            .partyJoinStatus(PartyJoinStatus.REQUEST)
            .requestReason(request.requestReason())
            .party(party)
            .build();
    }

}
