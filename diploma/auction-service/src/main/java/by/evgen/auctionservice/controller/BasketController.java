package by.evgen.auctionservice.controller;

import by.evgen.auctionservice.dto.AuctionDTO;
import by.evgen.auctionservice.dto.BasketDTO;
import by.evgen.auctionservice.dto.ProductDTO;
import by.evgen.auctionservice.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/basket")
public class BasketController {

    private static final String AUCTION_DELETED_BASKET_MESSAGE = "Auction with id - %s has been deleted from basket!";
    private static final String AUCTION_SAVED_BASKET_MESSAGE = "Auction with id - %s has been saved in basket!";
    private final BasketService basketService;
    private final RestTemplate template;

    @RequestMapping(value = "", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteAuctionFromBasket(@RequestHeader("id") Long userId,
                                                          @RequestParam("auctionId") Long auctionId) {
        BasketDTO basket = basketService.getBasketByUserId(userId);
        basketService.deleteAuctionFromBasketById(basket.getId(), auctionId);
        return new ResponseEntity<>(String.format(AUCTION_DELETED_BASKET_MESSAGE, auctionId), HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<AuctionDTO>> getBasket(@RequestHeader("id") Long userId,
                                               @RequestHeader("role") String role) {
        BasketDTO basket = basketService.getBasketByUserId(userId);
        setBasketWithProductData(userId, role, basket);
        return new ResponseEntity<>(basket.getAuctions(), HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<String> addAuctionToBasket(@RequestHeader("id") Long userId,
                                                     @RequestParam("auctionId") Long auctionId) {
        BasketDTO basket = basketService.getBasketByUserId(userId);
        basketService.addAuctionToBasket(basket.getId(), Long.valueOf(auctionId));
        return new ResponseEntity<>(String.format(AUCTION_SAVED_BASKET_MESSAGE, auctionId)
                , HttpStatus.OK);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void createBasket(@RequestHeader("id") Long userId) {
        basketService.createBasket(userId);
    }

    @RequestMapping(value = "/delete/{userId}", method = RequestMethod.DELETE)
    public void deleteBasket(@PathVariable Long userId) {
        basketService.deleteBasketByUserId(userId);
    }

    private HttpHeaders createHeadersForSecurity(Long userId, String role) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("id", userId.toString());
        headers.set("role", role);
        return headers;
    }

    private void setBasketWithProductData(Long userId, String role, BasketDTO basket) {
        ResponseEntity<List<ProductDTO>> response = template.exchange(
                "http://localhost:8084/api/v1/products",
                HttpMethod.GET,
                new HttpEntity<>("some body", createHeadersForSecurity(userId, role)),
                new ParameterizedTypeReference<>() {
                }
        );
        List<ProductDTO> products = response.getBody();
        if (products != null) {
            for (AuctionDTO auction : basket.getAuctions()) {
                for (ProductDTO product : products) {
                    if (auction.getProduct().getId().equals(product.getId())) {
                        auction.setProduct(product);
                    }
                }
            }
        }
    }
}
