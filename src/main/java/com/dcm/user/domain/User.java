package com.dcm.user.domain;

import com.dcm.auth.domain.OAuth;
import com.dcm.auth.dto.UserInfoResponse;
import com.dcm.global.domain.BaseEntity;
import com.dcm.job.domain.Job;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Entity
@Builder
@AllArgsConstructor
public class User extends BaseEntity {

    @Id
    private String email;

    private String nickname;

    private String phone;

    private String address;

    private String addressName;

    private String gender;

    private String picture;

    private OAuth platform;

    private String profileUpdateYN;

    @ManyToOne
    private Job job;

    protected User() {};

    public static User of(UserInfoResponse userInfo) {
        return User.builder()
                .email(userInfo.email())
                .nickname(userInfo.nickname())
                .picture(userInfo.picture())
                .platform(userInfo.platform())
                .build();
    }

}
