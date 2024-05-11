package com.dcm.party.domain;

import com.dcm.global.domain.BaseEntity;
import com.dcm.hobby.domain.Hobby;
import com.dcm.party.dto.PartyRequest;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@Table(name = "PARTY")
public class Party extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long partyId;

    @Column(nullable = false)
    private String managerId;

    @Column(nullable = false)
    private String partyName;

    @Min(value = 1) @Max(value = 300)
    @Column(nullable = false)
    private Integer capacity;

    private String meetAddress;

    private String meetAddressName;

    private String description;

    @ColumnDefault(value = "0")
    private Long vote;

    @OneToOne
    @JoinColumn(name = "hobbyId")
    private Hobby hobby;

    public static Party toEntity(PartyRequest request, Hobby hobby) {
        return Party.builder()
                .managerId(request.managerId())
                .partyName(request.partyName())
                .capacity(request.capacity())
                .meetAddress(request.meetAddress())
                .meetAddressName(request.meetAddressName())
                .description(request.description())
                .hobby(hobby)
                .build();
    }

}
