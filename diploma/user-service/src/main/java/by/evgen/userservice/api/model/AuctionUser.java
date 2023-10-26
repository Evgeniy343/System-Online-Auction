package by.evgen.userservice.api.model;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Setter
@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "auction_user")
public class AuctionUser {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "patronymic")
    private String patronymic;
    @Column(name = "email")
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "password")
    private String password;
    @Column(name = "photo")
    private String photo;
    @Column(name = "role")
    @Enumerated(value = EnumType.STRING)
    private Role role;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "registration_address_id")
    private RegistrationAddress registrationAddress;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "copies_of_documents_id")
    private CopiesOfDocuments copiesOfDocuments;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "identification_id")
    private Identification identification;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "requisites_for_return_of_deposit_id")
    private RequisitesForReturnOfDeposit requisitesForReturnOfDeposit;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuctionUser that = (AuctionUser) o;
        return Objects.equals(id, that.id)
                && Objects.equals(name, that.name)
                && Objects.equals(surname, that.surname)
                && Objects.equals(patronymic, that.patronymic)
                && Objects.equals(email, that.email)
                && Objects.equals(phoneNumber, that.phoneNumber)
                && Objects.equals(password, that.password)
                && Objects.equals(photo, that.photo)
                && role == that.role
                && Objects.equals(registrationAddress, that.registrationAddress)
                && Objects.equals(copiesOfDocuments, that.copiesOfDocuments)
                && Objects.equals(identification, that.identification)
                && Objects.equals(requisitesForReturnOfDeposit, that.requisitesForReturnOfDeposit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, patronymic, email, phoneNumber, password, photo, role,
                registrationAddress, copiesOfDocuments, identification, requisitesForReturnOfDeposit);
    }
}
