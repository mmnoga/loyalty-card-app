package pl.careaboutit.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.careaboutit.backend.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

}
