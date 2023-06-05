package by.evgen.auctionservice.repository;

import by.evgen.auctionservice.model.Bid;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BidRepository extends JpaRepository<Bid, Long> {
    List<Bid> findAllByUserId(Long userId);
}
