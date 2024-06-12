package com.dcm.party.domain.repository;

import com.dcm.party.domain.PartyJoinUser;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PartyJoinUserRepository extends JpaRepository<PartyJoinUser, Long> {

    @Query("SELECT ps FROM PartyJoinUser ps WHERE ps.email = :email")
    Optional<PartyJoinUser> findByEmail(String email);

    @Query("SELECT COUNT(ps) > 0 FROM PartyJoinUser ps WHERE ps.email = :email")
    boolean existsByEmail(String email);

}
