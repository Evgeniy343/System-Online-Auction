package by.evgen.productservice.mapper;

import by.evgen.productservice.dto.PhotoDTO;
import by.evgen.productservice.model.Photo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PhotoMapper {
    PhotoDTO convertPhotoToPhotoDTO(Photo photo);

    Photo convertPhotoDTOToPhoto(PhotoDTO photoDTO);


    void map(List<PhotoDTO> photoDTOS, @MappingTarget List<Photo> photos);
}
