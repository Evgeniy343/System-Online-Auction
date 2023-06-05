package by.evgen.auctionservice.mapper;

import by.evgen.auctionservice.dto.AuctionDTO;
import by.evgen.auctionservice.model.Auction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuctionMapper {
    @Mapping(target = "product.id", source = "productId")
    AuctionDTO convertAuctionToAuctionDTO(Auction auction);

    @Mapping(target = "productId", source = "product.id")
    Auction convertAuctionDTOToAuction(AuctionDTO auctionDTO);

    @Mapping(target = "id", ignore = true)
    void updateExisting(AuctionDTO auctionDTO, @MappingTarget Auction auction);
}
