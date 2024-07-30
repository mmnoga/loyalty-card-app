package pl.careaboutit.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.careaboutit.backend.model.SessionToken;

import java.util.Optional;

public interface SessionTokenRepository extends JpaRepository<SessionToken, Long> {

    Optional<SessionToken> findByToken(String token);

    boolean existsByToken(String token);
}
