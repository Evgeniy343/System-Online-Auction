package by.evgen.auctionservice.dto;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BidDTO {
    private Long id;

    private Long userId;

    private String suggestedPrice;

    private AuctionDTO auction;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BidDTO bidDTO = (BidDTO) o;
        return Objects.equals(id, bidDTO.id)
                && Objects.equals(userId, bidDTO.userId)
                && Objects.equals(suggestedPrice, bidDTO.suggestedPrice)
                && Objects.equals(auction, bidDTO.auction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, suggestedPrice, auction);
    }
}
