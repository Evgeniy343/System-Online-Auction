package by.evgen.auctionservice.model;

import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "basket")
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @OneToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "basket_auction",
            joinColumns = @JoinColumn(name = "basket_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "auction_id", referencedColumnName = "id")
    )
    private List<Auction> auctions;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Basket basket = (Basket) o;
        return Objects.equals(id, basket.id)
                && Objects.equals(userId, basket.userId)
                && Objects.equals(auctions, basket.auctions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, auctions);
    }
}
