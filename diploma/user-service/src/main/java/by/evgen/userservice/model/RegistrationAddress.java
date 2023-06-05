package by.evgen.userservice.model;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Setter
@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "registration_address")
public class RegistrationAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "country")
    @Enumerated(value = EnumType.STRING)
    private Country country;
    @Column(name = "region")
    private String region;
    @Column(name = "city")
    private String city;
    @Column(name = "street")
    private String street;
    @Column(name = "house")
    private String house;
    @Column(name = "flat")
    private String flat;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegistrationAddress that = (RegistrationAddress) o;
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
