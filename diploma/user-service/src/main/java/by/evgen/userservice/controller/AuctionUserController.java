package by.evgen.userservice.controller;

import by.evgen.userservice.dto.AuctionUserDTO;
import by.evgen.userservice.service.AuctionUserService;
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
@RequestMapping("/api/v1/users")
public class AuctionUserController {
    private static final String AUCTION_USER_DELETED_MESSAGE = "Auction user with id - %s has been deleted!";
    private static final String AUCTION_USER_UPDATED_MESSAGE = "Auction user with id - %s has been updated!";
    private static final String AUCTION_USER_SAVED_MESSAGE = "Auction user has been saved!";
    private final AuctionUserService userService;


    @RequestMapping(value = "/hello-user", method = RequestMethod.GET)
    public String hello() {
        return "hello-user";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<AuctionUserDTO> getAuctionUserById(@PathVariable @Min(0) Long id) {
        AuctionUserDTO auctionUserDTO = userService.findById(id);
        return new ResponseEntity<>(auctionUserDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public ResponseEntity<AuctionUserDTO> getAccount(@RequestHeader("id") Long userId) {
        AuctionUserDTO auctionUserDTO = userService.findById(userId);
        return new ResponseEntity<>(auctionUserDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<AuctionUserDTO>> getAuctionUsers() {
        List<AuctionUserDTO> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<String> saveAuctionUser(@Valid @RequestBody AuctionUserDTO auctionUserDTO) {
        userService.save(auctionUserDTO);
        return new ResponseEntity<>(String.format(AUCTION_USER_SAVED_MESSAGE), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateAuctionUserById(@Valid @RequestBody AuctionUserDTO auctionUserDTO
            , @PathVariable @Min(0) Long id) {
        userService.update(auctionUserDTO, id);
        return new ResponseEntity<>(String.format(AUCTION_USER_UPDATED_MESSAGE, id), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteAuctionUserById(@PathVariable @Min(0) Long id) {
        userService.delete(id);
        return new ResponseEntity<>(String.format(AUCTION_USER_DELETED_MESSAGE, id), HttpStatus.OK);
    }
}