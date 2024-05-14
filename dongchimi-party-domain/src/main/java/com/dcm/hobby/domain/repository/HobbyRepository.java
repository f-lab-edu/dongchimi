package com.dcm.hobby.domain.repository;

import com.dcm.hobby.domain.Hobby;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HobbyRepository extends JpaRepository<Hobby, Long> {
}
