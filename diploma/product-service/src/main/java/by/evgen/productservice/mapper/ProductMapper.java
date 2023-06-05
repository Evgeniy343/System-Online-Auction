package by.evgen.productservice.mapper;

import by.evgen.productservice.dto.ProductDTO;
import by.evgen.productservice.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {PhotoMapper.class})
public interface ProductMapper {
    ProductDTO convertProductToProductDTO(Product product);

    Product convertProductDTOToProduct(ProductDTO productDTO);

    @Mapping(target = "id", ignore = true)
    void updateExisting(ProductDTO productDTO, @MappingTarget Product product);
}
