package by.evgen.userservice.api.dto;

import by.evgen.userservice.api.model.Role;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuctionUserDTO {
    private Long id;
    @NotBlank(message = "Name is mandatory!")
    private String name;
    @NotBlank(message = "Surname is mandatory!")
    private String surname;
    @NotBlank(message = "Patronymic is mandatory!")
    private String patronymic;
    @NotBlank(message = "Password is mandatory!")
    private String password;
    private String photo;
    @NotBlank(message = "Email is mandatory!")
    @Email(message = "Email is not corrected!")
    private String email;
    @Pattern(regexp = "^(\\+375)\\((29|25|44|33)\\)(\\d{3})-(\\d{2})-(\\d{2})$",
            message = "Phone number is not corrected! Example: +375(33)684-91-06")
    private String phoneNumber;
    private Role role;
    private RegistrationAddressDTO registrationAddress;
    private CopiesOfDocumentsDTO copiesOfDocuments;
    private IdentificationDTO identification;
    private RequisitesForReturnOfDepositDTO requisitesForReturnOfDeposit;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuctionUserDTO that = (AuctionUserDTO) o;
        return Objects.equals(name, that.name)
                && Objects.equals(surname, that.surname)
                && Objects.equals(password, that.password)
                && Objects.equals(photo, that.photo)
                && Objects.equals(email, that.email)
                && Objects.equals(phoneNumber, that.phoneNumber)
                && role == that.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, password, photo, email, phoneNumber, role);
    }
}
