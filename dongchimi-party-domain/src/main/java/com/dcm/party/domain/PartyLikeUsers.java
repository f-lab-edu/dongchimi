package com.dcm.party.domain;

import com.dcm.global.domain.BaseEntity;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@Table(name = "PARTY_LIKE_USERS")
public class PartyLikeUsers extends BaseEntity {

    @EmbeddedId
    private PartyLikeUsersPK partyLikeUsersIds;

}
