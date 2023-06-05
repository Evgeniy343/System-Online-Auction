package by.evgen.userservice.dto;

import by.evgen.userservice.model.Country;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Objects;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationAddressDTO {

    private Long id;
    @Enumerated(value = EnumType.STRING)
    private Country country;
    private String region;
    private String city;
    private String street;
    private String house;
    private String flat;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegistrationAddressDTO that = (RegistrationAddressDTO) o;
        return Objects.equals(id, that.id)
                && country == that.country
                && Objects.equals(region, that.region)
                && Objects.equals(city, that.city)
                && Objects.equals(street, that.street)
                && Objects.equals(house, that.house)
                && Objects.equals(flat, that.flat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, country, region, city, street, house, flat);
    }
}
