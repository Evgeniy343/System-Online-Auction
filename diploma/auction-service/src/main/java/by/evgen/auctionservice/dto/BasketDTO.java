package by.evgen.auctionservice.dto;

import lombok.*;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BasketDTO {
    private Long id;
    private Long userId;
    private List<AuctionDTO> auctions;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasketDTO basketDTO = (BasketDTO) o;
        return Objects.equals(id, basketDTO.id)
                && Objects.equals(userId, basketDTO.userId)
                && Objects.equals(auctions, basketDTO.auctions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, auctions);
    }
}
