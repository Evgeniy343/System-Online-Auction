package by.evgen.userservice.api.dto;

import lombok.*;

import java.util.Objects;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequisitesForReturnOfDepositDTO {
    private Long id;
    private String currentAccountNumber;
    private String bankNameAndBranchNumber;
    private String bankCode;
    private String bankAddress;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequisitesForReturnOfDepositDTO that = (RequisitesForReturnOfDepositDTO) o;
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
