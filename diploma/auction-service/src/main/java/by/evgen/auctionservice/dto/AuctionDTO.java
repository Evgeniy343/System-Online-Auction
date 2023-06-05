package by.evgen.auctionservice.dto;

import by.evgen.auctionservice.model.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.sql.Timestamp;
import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuctionDTO {
    private Long id;
    private String initialRate;
    private String depositAmount;
    private String fullPaymentTerm;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp tradingStartTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp tradingCloseTime;
    private ProductDTO product;
    private Long userId;
    private Status status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuctionDTO that = (AuctionDTO) o;
        return Objects.equals(id, that.id)
                && Objects.equals(initialRate, that.initialRate)
                && Objects.equals(depositAmount, that.depositAmount)
                && Objects.equals(fullPaymentTerm, that.fullPaymentTerm)
                && Objects.equals(tradingStartTime, that.tradingStartTime)
                && Objects.equals(tradingCloseTime, that.tradingCloseTime)
                && Objects.equals(status, that.status)
                && Objects.equals(product, that.product) && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, initialRate, depositAmount, fullPaymentTerm, tradingStartTime,
                tradingCloseTime, product, userId, status);
    }
}
