package by.evgen.userservice.api.mapper;

import by.evgen.userservice.api.dto.IdentificationDTO;
import by.evgen.userservice.api.model.Identification;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IdentificationMapper {
    IdentificationDTO convertIdentificationDTOToIdentificationDTODTO(Identification identification);

    Identification convertIdentificationDTODTOToIdentificationDTO(IdentificationDTO identificationDTO);

    @Mapping(target = "id", ignore = true)
    void updateExisting(IdentificationDTO identificationDTO, @MappingTarget Identification identification);
}
