package by.evgen.authenticationservice.repository;

import by.evgen.authenticationservice.model.AuctionUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuctionUserRepository extends JpaRepository<AuctionUser, Long> {
    Optional<AuctionUser> findByEmail(String email);
}
