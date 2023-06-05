package by.evgen.cardservice.model;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "card")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "card_number")
    private String cardNumber;
    @Column(name = "owner_name")
    private String owner_name;
    @Column(name = "owner_surname")
    private String owner_surname;
    @Column(name = "before_date")
    private String beforeDate;
    @Column(name = "user_id")
    private Long userId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return Objects.equals(id, card.id)
                && Objects.equals(cardNumber, card.cardNumber)
                && Objects.equals(owner_name, card.owner_name)
                && Objects.equals(owner_surname, card.owner_surname)
                && Objects.equals(beforeDate, card.beforeDate)
                && Objects.equals(userId, card.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cardNumber, owner_name, owner_surname, beforeDate, userId);
    }
}
