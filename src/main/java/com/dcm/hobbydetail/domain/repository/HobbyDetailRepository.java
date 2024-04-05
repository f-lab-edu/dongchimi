package com.dcm.hobbydetail.domain.repository;


import com.dcm.hobbydetail.domain.HobbyDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HobbyDetailRepository extends JpaRepository<HobbyDetail, Long> {
}
