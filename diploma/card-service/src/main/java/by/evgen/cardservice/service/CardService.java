package by.evgen.cardservice.service;

import by.evgen.cardservice.dto.CardDTO;
import by.evgen.cardservice.exception.CardNotFoundException;
import by.evgen.cardservice.mapper.CardMapper;
import by.evgen.cardservice.model.Card;
import by.evgen.cardservice.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardService {

    private static final String CARD_NOT_FOUND_MESSAGE = "Card with id - %s is not found!";
    private static final String CARDS_ARE_OUT_OF_STOCK_MESSAGE = "Cards are out of stock!";
    private final CardRepository cardRepository;
    private final CardMapper cardMapper;

    public CardDTO findById(Long id) {
        Card card = cardRepository.findById(id).orElseThrow(() ->
                new CardNotFoundException(String.format(CARD_NOT_FOUND_MESSAGE, id)));
        return cardMapper.convertCardToCardDTO(card);
    }

    public List<CardDTO> findAllByUserId(Long userId) {
        List<CardDTO> cards = cardRepository.findAllByUserId(userId)
                .stream()
                .map(cardMapper::convertCardToCardDTO)
                .toList();
        if (cards.isEmpty()) {
            throw new CardNotFoundException(CARDS_ARE_OUT_OF_STOCK_MESSAGE);
        } else {
            return cards;
        }
    }

    public CardDTO save(CardDTO cardDTO) {
        Card card = cardMapper.convertCardDTOToCard(cardDTO);
        Card savedCard = cardRepository.save(card);
        return cardMapper.convertCardToCardDTO(savedCard);
    }

    public CardDTO update(CardDTO cardDTO, Long id) {
        Card card = cardRepository.findById(id).orElseThrow(() ->
                new CardNotFoundException(String.format(CARD_NOT_FOUND_MESSAGE, id)));
        cardMapper.updateExisting(cardDTO, card);
        Card updatedCard = cardRepository.save(card);
        return cardMapper.convertCardToCardDTO(updatedCard);
    }

    public void delete(Long id) {
        Card card = cardRepository.findById(id).orElseThrow(() ->
                new CardNotFoundException(String.format(CARD_NOT_FOUND_MESSAGE, id)));
        cardRepository.deleteById(id);
    }
}
