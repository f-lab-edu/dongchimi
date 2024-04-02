package com.dcm.hobby.domain;

import com.dcm.global.domain.BaseEntity;
import com.dcm.hobby_detail.domain.HobbyDetail;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "HOBBY")
public class Hobby extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hobbyId;

    @Column(nullable = false)
    private String hobbyName;

    @Column(nullable = false)
    private String useYn;

    @OneToMany(mappedBy = "hobby", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<HobbyDetail> hobbyDetails = new ArrayList<>();


}
