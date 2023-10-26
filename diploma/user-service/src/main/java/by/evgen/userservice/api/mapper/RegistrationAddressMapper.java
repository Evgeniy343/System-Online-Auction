package by.evgen.userservice.api.mapper;

import by.evgen.userservice.api.dto.RegistrationAddressDTO;
import by.evgen.userservice.api.model.RegistrationAddress;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RegistrationAddressMapper {
    RegistrationAddressDTO convertRegistrationAddressToRegistrationAddressDTO(RegistrationAddress registrationAddress);

    RegistrationAddress convertRegistrationAddressDTOToRegistrationAddress
            (RegistrationAddressDTO registrationAddressDTO);

    @Mapping(target = "id", ignore = true)
    void updateExisting(RegistrationAddressDTO registrationAddressDTO,
                        @MappingTarget RegistrationAddress registrationAddress);
}
