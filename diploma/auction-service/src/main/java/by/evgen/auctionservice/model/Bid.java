package by.evgen.auctionservice.model;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bid")
public class Bid {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "suggested_price")
    private String suggestedPrice;
    @ManyToOne
    @JoinColumn(name = "auction_id")
    private Auction auction;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bid bid = (Bid) o;
        return Objects.equals(id, bid.id)
                && Objects.equals(userId, bid.userId)
                && Objects.equals(suggestedPrice, bid.suggestedPrice)
                && Objects.equals(auction, bid.auction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, suggestedPrice, auction);
    }
}
