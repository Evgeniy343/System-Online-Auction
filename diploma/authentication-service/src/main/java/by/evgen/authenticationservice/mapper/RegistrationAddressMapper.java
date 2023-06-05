package by.evgen.authenticationservice.mapper;

import by.evgen.authenticationservice.dto.RegistrationAddressDTO;
import by.evgen.authenticationservice.model.RegistrationAddress;
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
