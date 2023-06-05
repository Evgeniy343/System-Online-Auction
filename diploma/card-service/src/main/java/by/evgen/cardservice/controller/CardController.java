package by.evgen.cardservice.controller;

import by.evgen.cardservice.dto.CardDTO;
import by.evgen.cardservice.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;


@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cards")
public class CardController {
    private static final String CARD_DELETED_MESSAGE = "Card with id - %s has been deleted!";
    private static final String CARD_UPDATED_MESSAGE = "Card with id - %s has been updated!";
    private static final String CARD_SAVED_MESSAGE = "Card has been saved!";
    private final CardService cardService;

    @RequestMapping(value = "/hello-card", method = RequestMethod.GET)
    public String hello() {
        return "hello-card";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<CardDTO> getCardById(@PathVariable @Min(0) Long id) {
        CardDTO cardDTO = cardService.findById(id);
        return new ResponseEntity<>(cardDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<CardDTO>> getCards(@RequestHeader("id") Long userId) {
        List<CardDTO> cards = cardService.findAllByUserId(userId);
        return new ResponseEntity<>(cards, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<String> saveCard(@RequestHeader("id") Long userId, @Valid @RequestBody CardDTO cardDTO) {
        cardDTO.setUserId(userId);
        cardService.save(cardDTO);
        return new ResponseEntity<>(String.format(CARD_SAVED_MESSAGE), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateCardById(@Valid @RequestBody CardDTO cardDTO
            , @PathVariable @Min(0) Long id) {
        cardService.update(cardDTO, id);
        return new ResponseEntity<>(String.format(CARD_UPDATED_MESSAGE, id), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteCardById(@PathVariable @Min(0) Long id) {
        cardService.delete(id);
        return new ResponseEntity<>(String.format(CARD_DELETED_MESSAGE, id), HttpStatus.OK);
    }


}
