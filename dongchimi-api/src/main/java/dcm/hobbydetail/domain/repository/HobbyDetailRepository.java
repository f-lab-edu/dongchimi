package dcm.hobbydetail.domain.repository;


import dcm.hobbydetail.domain.HobbyDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HobbyDetailRepository extends JpaRepository<HobbyDetail, Long> {
}
