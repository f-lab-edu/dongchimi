package com.dcm.hobby_detail.domain;

import com.dcm.global.domain.BaseEntity;
import com.dcm.hobby.domain.Hobby;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
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

}
