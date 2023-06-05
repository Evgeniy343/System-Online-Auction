package by.evgen.authenticationservice.mapper;

import by.evgen.authenticationservice.annotation.ForAuthenticate;
import by.evgen.authenticationservice.dto.AuctionUserDTO;
import by.evgen.authenticationservice.model.AuctionUser;
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

    @ForAuthenticate
    AuctionUserDTO convertUserToUserDTOForAuthenticate(AuctionUser auctionUser);

    @ForAuthenticate
    AuctionUser convertUserDTOToUserForAuthenticate(AuctionUserDTO auctionUserDTO);
}
