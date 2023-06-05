package by.evgen.auctionservice.mapper;

import by.evgen.auctionservice.dto.BasketDTO;
import by.evgen.auctionservice.model.Basket;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {AuctionMapper.class})
public interface BasketMapper {
    BasketDTO convertBasketToBasketDTO(Basket basket);
}
