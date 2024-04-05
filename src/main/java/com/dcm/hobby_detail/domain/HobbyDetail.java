package com.dcm.hobby_detail.domain;

import com.dcm.global.domain.BaseEntity;
import com.dcm.hobby.domain.Hobby;
import com.dcm.hobby_detail.dto.HobbyDetailRequest;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "HOBBY_DETAIL")
public class HobbyDetail extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hobbyDetailId;

    @Column(nullable = false)
    private String hobbyDetailName;

    @Column(nullable = false)
    private String useYn;

    @ManyToOne
    @JoinColumn(name = "hobbyId")
    private Hobby hobby;

    public static HobbyDetail of(String hobbyDetailName, String useYn, Hobby hobby) {
        return HobbyDetail.builder()
                .hobbyDetailName(hobbyDetailName)
                .useYn(useYn)
                .hobby(hobby)
                .build();
    }

    public static HobbyDetail of(Long hobbyDetailId, String hobbyDetailName, String useYn, Hobby hobby) {
        return HobbyDetail.builder()
                .hobbyDetailId(hobbyDetailId)
                .hobbyDetailName(hobbyDetailName)
                .useYn(useYn)
                .hobby(hobby)
                .build();
    }

}
