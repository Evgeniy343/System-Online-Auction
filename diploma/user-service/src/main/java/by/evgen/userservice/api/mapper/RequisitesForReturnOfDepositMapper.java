package by.evgen.userservice.api.mapper;

import by.evgen.userservice.api.dto.RequisitesForReturnOfDepositDTO;
import by.evgen.userservice.api.model.RequisitesForReturnOfDeposit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RequisitesForReturnOfDepositMapper {
    RequisitesForReturnOfDepositDTO convertRequisitesForReturnOfDepositToRequisitesForReturnOfDepositDTO
            (RequisitesForReturnOfDeposit registrationAddress);

    RequisitesForReturnOfDeposit convertRequisitesForReturnOfDepositDTOToRequisitesForReturnOfDeposit
            (RequisitesForReturnOfDepositDTO registrationAddressDTO);

    @Mapping(target = "id", ignore = true)
    void updateExisting(RequisitesForReturnOfDepositDTO registrationAddressDTO,
                        @MappingTarget RequisitesForReturnOfDeposit registrationAddress);
}
