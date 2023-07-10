package by.evgen.userservice.controller;

import by.evgen.userservice.dto.AuctionUserDTO;
import by.evgen.userservice.model.Role;
import by.evgen.userservice.service.AuctionUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

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
    private final RestTemplate template;


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
        AuctionUserDTO savedUser = userService.save(auctionUserDTO);
        if (!savedUser.getRole().equals(Role.ADMIN)) {
            template.exchange(
                    "http://localhost:8085/api/v1/basket/create",
                    HttpMethod.POST,
                    new HttpEntity<>("some body",
                            createHeadersForSecurity(savedUser.getId(), savedUser.getRole().name())),
                    new ParameterizedTypeReference<>() {
                    }
            );
        }
        return new ResponseEntity<>(String.format(AUCTION_USER_SAVED_MESSAGE), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateAuctionUserById(@Valid @RequestBody AuctionUserDTO auctionUserDTO
            , @PathVariable @Min(0) Long id) {
        userService.update(auctionUserDTO, id);
        return new ResponseEntity<>(String.format(AUCTION_USER_UPDATED_MESSAGE, id), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteAuctionUserById(@RequestHeader("id") Long userId,
                                                        @RequestHeader("role") String role,
                                                        @PathVariable @Min(0) Long id) {
        AuctionUserDTO deletedUser = userService.delete(id);
        if (!deletedUser.getRole().equals(Role.ADMIN)) {
            template.exchange(
                    "http://localhost:8085/api/v1/basket/delete/"+ id,
                    HttpMethod.DELETE,
                    new HttpEntity<>("some body",
                            createHeadersForSecurity(userId, role)),
                    new ParameterizedTypeReference<>() {
                    }
            );
            template.exchange(
                    "http://localhost:8085/api/v1/auctions/delete/" + id,
                    HttpMethod.DELETE,
                    new HttpEntity<>("some body",
                            createHeadersForSecurity(userId, role)),
                    new ParameterizedTypeReference<>() {
                    }
            );
            template.exchange(
                    "http://localhost:8085/api/v1/bids/delete/" + id,
                    HttpMethod.DELETE,
                    new HttpEntity<>("some body",
                            createHeadersForSecurity(userId, role)),
                    new ParameterizedTypeReference<>() {
                    }
            );
        }
        return new ResponseEntity<>(String.format(AUCTION_USER_DELETED_MESSAGE, id), HttpStatus.OK);
    }

    private HttpHeaders createHeadersForSecurity(Long userId, String role) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("id", userId.toString());
        headers.set("role", role);
        return headers;
    }
}