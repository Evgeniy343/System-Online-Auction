package by.evgen.auctionservice.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "auction")
public class Auction {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "initial_rate")
    private String initialRate;
    @Column(name = "deposit_amount")
    private String depositAmount;
    @Column(name = "full_payment_term")
    private String fullPaymentTerm;
    @Column(name = "trading_start_time")
    private Timestamp tradingStartTime;
    @Column(name = "trading_close_time")
    private Timestamp tradingCloseTime;
    @Column(name = "product_id")
    private Long productId;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Auction auction = (Auction) o;
        return Objects.equals(id, auction.id)
                && Objects.equals(initialRate, auction.initialRate)
                && Objects.equals(depositAmount, auction.depositAmount)
                && Objects.equals(fullPaymentTerm, auction.fullPaymentTerm)
                && Objects.equals(tradingStartTime, auction.tradingStartTime)
                && Objects.equals(tradingCloseTime, auction.tradingCloseTime)
                && Objects.equals(productId, auction.productId)
                && Objects.equals(status, auction.status)
                && Objects.equals(userId, auction.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, initialRate, depositAmount, fullPaymentTerm, tradingStartTime,
                tradingCloseTime, productId, userId, status);
    }
}
