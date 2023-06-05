package by.evgen.cardservice.mapper;

import by.evgen.cardservice.dto.CardDTO;
import by.evgen.cardservice.model.Card;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CardMapper {
    CardDTO convertCardToCardDTO(Card card);

    Card convertCardDTOToCard(CardDTO cardDTO);

    void updateExisting(CardDTO cardDTO, @MappingTarget Card card);
}
