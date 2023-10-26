package by.evgen.userservice.api.repository;

import by.evgen.userservice.api.model.AuctionUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuctionUserRepository extends JpaRepository<AuctionUser, Long> {
}
