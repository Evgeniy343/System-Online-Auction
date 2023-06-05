package by.evgen.auctionservice.repository;

import by.evgen.auctionservice.model.Auction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuctionRepository extends JpaRepository<Auction, Long> {
    List<Auction> findAllByUserId(Long userId);
}
