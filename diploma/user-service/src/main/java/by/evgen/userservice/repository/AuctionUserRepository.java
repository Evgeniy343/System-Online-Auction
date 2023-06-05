package by.evgen.userservice.repository;

import by.evgen.userservice.model.AuctionUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuctionUserRepository extends JpaRepository<AuctionUser, Long> {
}
