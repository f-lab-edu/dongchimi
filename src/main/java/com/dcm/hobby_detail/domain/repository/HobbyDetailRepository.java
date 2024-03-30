package com.dcm.hobby_detail.domain.repository;


import com.dcm.hobby_detail.domain.HobbyDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HobbyDetailRepository extends JpaRepository<HobbyDetail, Long> {
}
