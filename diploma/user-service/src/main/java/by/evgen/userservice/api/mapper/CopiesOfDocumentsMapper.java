package by.evgen.userservice.api.mapper;

import by.evgen.userservice.api.dto.CopiesOfDocumentsDTO;
import by.evgen.userservice.api.model.CopiesOfDocuments;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CopiesOfDocumentsMapper {
    CopiesOfDocumentsDTO convertCopiesOfDocumentsToCopiesOfDocumentsDTO(CopiesOfDocuments copiesOfDocuments);

    CopiesOfDocuments convertCopiesOfDocumentsDTOToCopiesOfDocuments(CopiesOfDocumentsDTO copiesOfDocumentsDTO);

    @Mapping(target = "id", ignore = true)
    void updateExisting(CopiesOfDocumentsDTO copiesOfDocumentsDTO, @MappingTarget CopiesOfDocuments copiesOfDocuments);
}
