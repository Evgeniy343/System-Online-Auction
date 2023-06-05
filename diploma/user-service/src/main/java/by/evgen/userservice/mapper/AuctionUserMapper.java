package by.evgen.userservice.mapper;

import by.evgen.userservice.dto.AuctionUserDTO;
import by.evgen.userservice.model.AuctionUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {CopiesOfDocumentsMapper.class, IdentificationMapper.class, RegistrationAddressMapper.class
                , RequisitesForReturnOfDepositMapper.class})
public interface AuctionUserMapper {
    AuctionUserDTO convertAuctionUserToAuctionUserDTO(AuctionUser auctionUser);

    AuctionUser convertAuctionUserDTOToAuctionUser(AuctionUserDTO auctionUserDTO);

    @Mapping(target = "id", ignore = true)
    void updateExisting(AuctionUserDTO auctionUserDTO, @MappingTarget AuctionUser auctionUser);
}
