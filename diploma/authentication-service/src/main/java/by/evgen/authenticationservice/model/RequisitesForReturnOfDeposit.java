package by.evgen.authenticationservice.model;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Setter
@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "requisites_for_return_of_deposit")
public class RequisitesForReturnOfDeposit {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "current_account_number")
    private String currentAccountNumber;
    @Column(name = "bank_name_and_branch_number")
    private String bankNameAndBranchNumber;
    @Column(name = "bank_code")
    private String bankCode;
    @Column(name = "bank_address")
    private String bankAddress;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequisitesForReturnOfDeposit that = (RequisitesForReturnOfDeposit) o;
        return Objects.equals(id, that.id)
                && Objects.equals(currentAccountNumber, that.currentAccountNumber)
                && Objects.equals(bankNameAndBranchNumber, that.bankNameAndBranchNumber)
                && Objects.equals(bankCode, that.bankCode)
                && Objects.equals(bankAddress, that.bankAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, currentAccountNumber, bankNameAndBranchNumber, bankCode, bankAddress);
    }
}
