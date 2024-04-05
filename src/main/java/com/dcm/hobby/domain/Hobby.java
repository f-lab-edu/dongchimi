package com.dcm.hobby.domain;

import com.dcm.global.domain.BaseEntity;
import com.dcm.global.enumurate.YN;
import com.dcm.hobbydetail.domain.HobbyDetail;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "HOBBY")
public class Hobby extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hobbyId;

    @Column(nullable = false)
    private String hobbyName;

    @Column(nullable = false)
    private YN useYn;

    @OneToMany(mappedBy = "hobby", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<HobbyDetail> hobbyDetails = new ArrayList<>();

    public static Hobby of(String hobbyName, YN useYn) {
        return Hobby.builder()
                .hobbyName(hobbyName)
                .useYn(useYn)
                .build();
    }

    public static Hobby of(Long hobbyId, String hobbyName, YN useYn) {
        return Hobby.builder()
                .hobbyId(hobbyId)
                .hobbyName(hobbyName)
                .useYn(useYn)
                .build();
    }
}
