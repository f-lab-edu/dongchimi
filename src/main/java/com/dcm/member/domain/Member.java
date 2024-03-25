package com.dcm.member.domain;

import com.dcm.auth.domain.OAuth;
import com.dcm.auth.dto.UserInfoResponse;
import com.dcm.global.domain.BaseEntity;
import com.dcm.job.domain.Job;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@Table(name = "MEMBER")
public class Member extends BaseEntity {

    @Id
    private String email;

    private String nickname;

    private String phone;

    private String address;

    private String addressName;

    private String gender;

    private String picture;

    private OAuth platform;

    private String profileUpdateYn;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Job> job = new ArrayList<>();

    protected Member() {};

    public static Member of(UserInfoResponse userInfo) {
        return Member.builder()
                .email(userInfo.email())
                .nickname(userInfo.nickname())
                .picture(userInfo.picture())
                .platform(userInfo.platform())
                .build();
    }

}
