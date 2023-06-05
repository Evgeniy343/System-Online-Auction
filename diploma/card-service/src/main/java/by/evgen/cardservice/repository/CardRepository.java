package by.evgen.cardservice.repository;

import by.evgen.cardservice.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findAllByUserId(Long userId);
}
