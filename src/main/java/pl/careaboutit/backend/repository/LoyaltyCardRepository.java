package pl.careaboutit.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.careaboutit.backend.model.LoyaltyCard;
import pl.careaboutit.backend.model.User;

import java.util.Optional;

@Repository
public interface LoyaltyCardRepository extends JpaRepository<LoyaltyCard, Long> {

    Optional<LoyaltyCard> findByCardNumber(String cardNumber);

    Optional<LoyaltyCard> findByUser(User user);

}
