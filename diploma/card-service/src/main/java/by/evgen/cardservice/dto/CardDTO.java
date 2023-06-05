package by.evgen.cardservice.dto;

import lombok.*;

import javax.validation.constraints.Pattern;
import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardDTO {
    @Pattern(regexp = "^(\\d{4}) (\\d{4}) (\\d{4}) (\\d{4})$",
            message = "Card number is not corrected! Example: 8888 8888 8888 8888")
    private String cardNumber;
    private String owner_name;
    private String owner_surname;
    @Pattern(regexp = "^(\\d{2})/(\\d{2})$",
            message = "Before date is not corrected! Example: 02/28")
    private String beforeDate;
    private Long userId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardDTO cardDTO = (CardDTO) o;
        return Objects.equals(cardNumber, cardDTO.cardNumber)
                && Objects.equals(owner_name, cardDTO.owner_name)
                && Objects.equals(owner_surname, cardDTO.owner_surname)
                && Objects.equals(beforeDate, cardDTO.beforeDate)
                && Objects.equals(userId, cardDTO.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardNumber, owner_name, owner_surname, beforeDate, userId);
    }
}
