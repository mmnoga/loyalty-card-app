package pl.careaboutit.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.careaboutit.backend.model.LoyaltyCard;
import pl.careaboutit.backend.model.LoyaltyPoint;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoyaltyPointRepository extends JpaRepository<LoyaltyPoint, Long> {

    List<LoyaltyPoint> findByCard_Id(Long cardId);

    Optional<LoyaltyPoint> findByCard(LoyaltyCard cardToUpdate);

}
