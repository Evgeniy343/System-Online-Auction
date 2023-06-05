package by.evgen.auctionservice.mapper;

import by.evgen.auctionservice.dto.BidDTO;
import by.evgen.auctionservice.model.Bid;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {AuctionMapper.class})
public interface BidMapper {
    BidDTO convertBidToBidDTO(Bid bid);

    @Mapping(target = "auction.id", source = "auction.id")
    @Mapping(target = "auction.initialRate", ignore = true)
    @Mapping(target = "auction.depositAmount", ignore = true)
    @Mapping(target = "auction.fullPaymentTerm", ignore = true)
    @Mapping(target = "auction.tradingStartTime", ignore = true)
    @Mapping(target = "auction.tradingCloseTime", ignore = true)
    @Mapping(target = "auction.productId", ignore = true)
    @Mapping(target = "auction.userId", ignore = true)
    @Mapping(target = "auction.status", ignore = true)
    Bid convertBidDTOToBid(BidDTO bidDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "auction.initialRate", ignore = true)
    @Mapping(target = "auction.depositAmount", ignore = true)
    @Mapping(target = "auction.fullPaymentTerm", ignore = true)
    @Mapping(target = "auction.tradingStartTime", ignore = true)
    @Mapping(target = "auction.tradingCloseTime", ignore = true)
    @Mapping(target = "auction.productId", ignore = true)
    @Mapping(target = "auction.userId", ignore = true)
    @Mapping(target = "auction.status", ignore = true)
    void updateExisting(BidDTO bidDTO, @MappingTarget Bid bid);
}
