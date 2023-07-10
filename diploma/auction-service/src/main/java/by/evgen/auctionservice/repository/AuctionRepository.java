package by.evgen.auctionservice.repository;

import by.evgen.auctionservice.model.Auction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AuctionRepository extends JpaRepository<Auction, Long> {
    List<Auction> findAllByUserId(Long userId);
    void deleteAllByUserId(Long userId);

}
