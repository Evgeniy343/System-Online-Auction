package by.evgen.userservice.mapper;

import by.evgen.userservice.dto.IdentificationDTO;
import by.evgen.userservice.model.Identification;
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
