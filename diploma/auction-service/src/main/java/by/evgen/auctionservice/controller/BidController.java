package by.evgen.auctionservice.controller;

import by.evgen.auctionservice.dto.AuctionDTO;
import by.evgen.auctionservice.dto.BidDTO;
import by.evgen.auctionservice.dto.ProductDTO;
import by.evgen.auctionservice.service.AuctionService;
import by.evgen.auctionservice.service.BidService;
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
@RequestMapping("api/v1/bids")
public class BidController {
    private static final String BID_DELETED_MESSAGE = "Bid with id - %s has been deleted!";
    private static final String BID_UPDATED_MESSAGE = "Bid with id - %s has been updated!";
    private static final String BID_SAVED_MESSAGE = "Bid has been saved!";
    private final BidService bidService;
    private final AuctionService auctionService;
    private final RestTemplate template;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<BidDTO> getBidById(@RequestHeader("id") Long userId,
                                             @RequestHeader("role") String role,
                                             @PathVariable @Min(0) Long id) {
        BidDTO bidDTO = bidService.findById(id);
        ResponseEntity<ProductDTO> response = template.exchange(
                "http://localhost:8084/api/v1/products/" + bidDTO.getAuction().getProduct().getId(),
                HttpMethod.GET,
                new HttpEntity<>("some body", createHeadersForSecurity(userId, role)),
                new ParameterizedTypeReference<>() {
                }
        );
        ProductDTO product = response.getBody();
        bidDTO.getAuction().setProduct(product);
        return new ResponseEntity<>(bidDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<BidDTO>> getBids(@RequestHeader("id") Long userId,
                                                @RequestHeader("role") String role) {
        List<BidDTO> bids = bidService.findAll();
        setBidsWithProductData(userId, role, bids);
        return new ResponseEntity<>(bids, HttpStatus.OK);
    }

    @RequestMapping(value = "/my", method = RequestMethod.GET)
    public ResponseEntity<List<BidDTO>> getUserBids(@RequestHeader("id") Long userId,
                                                    @RequestHeader("role") String role) {
        List<BidDTO> bids = bidService.findAllByUserId(userId);
        setBidsWithProductData(userId, role, bids);
        return new ResponseEntity<>(bids, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<String> saveBid(@RequestHeader("id") Long userId,
                                          @RequestParam("auctionId") Long auctionId,
                                          @RequestParam("suggested_price") String suggestedPrice) {
        AuctionDTO auctionDTO = auctionService.findById(auctionId);
        BidDTO bidDTO = BidDTO.builder()
                .userId(userId)
                .auction(auctionDTO)
                .suggestedPrice(suggestedPrice)
                .build();
        bidService.save(bidDTO);
        return new ResponseEntity<>(String.format(BID_SAVED_MESSAGE), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateBidById(@RequestHeader("id") Long userId,
                                                @RequestParam("auctionId") Long auctionId,
                                                @RequestParam("suggested_price") String suggestedPrice,
                                                @PathVariable @Min(0) Long id) {
        AuctionDTO auctionDTO = auctionService.findById(auctionId);
        BidDTO bidDTO = BidDTO.builder()
                .auction(auctionDTO)
                .suggestedPrice(suggestedPrice)
                .userId(userId)
                .build();
        bidService.update(bidDTO, id);
        return new ResponseEntity<>(String.format(BID_UPDATED_MESSAGE, id), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteBidById(@PathVariable @Min(0) Long id) {
        bidService.delete(id);
        return new ResponseEntity<>(String.format(BID_DELETED_MESSAGE, id), HttpStatus.OK);
    }

    private HttpHeaders createHeadersForSecurity(Long userId, String role) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("id", userId.toString());
        headers.set("role", role);
        return headers;
    }

    private void setBidsWithProductData(Long userId, String role, List<BidDTO> bids) {
        ResponseEntity<List<ProductDTO>> response = template.exchange(
                "http://localhost:8084/api/v1/products",
                HttpMethod.GET,
                new HttpEntity<>("some body", createHeadersForSecurity(userId, role)),
                new ParameterizedTypeReference<>() {
                }
        );
        List<ProductDTO> products = response.getBody();
        if (products != null) {
            for (BidDTO bid : bids) {
                for (ProductDTO product : products) {
                    if (bid.getAuction().getProduct().getId().equals(product.getId())) {
                        bid.getAuction().setProduct(product);
                    }
                }
            }
        }
    }

}
