package by.evgen.auctionservice.repository;

import by.evgen.auctionservice.model.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BasketRepository extends JpaRepository<Basket, Long> {
    Optional<Basket> findBasketByUserId(Long userId);
}
