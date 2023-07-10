package by.evgen.auctionservice.controller;

import by.evgen.auctionservice.dto.AuctionDTO;
import by.evgen.auctionservice.dto.ProductDTO;
import by.evgen.auctionservice.model.Status;
import by.evgen.auctionservice.service.AuctionService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auctions")
public class AuctionController {
    private static final String AUCTION_DELETED_MESSAGE = "Auction with id - %s has been deleted!";
    private static final String AUCTION_UPDATED_MESSAGE = "Auction with id - %s has been updated!";
    private static final String AUCTION_SAVED_MESSAGE = "Auction has been saved!";
    private final AuctionService auctionService;
    private final RestTemplate template;

    @RequestMapping(value = "/hello-auction", method = RequestMethod.GET)
    public String hello() {
        return "hello-auction";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<AuctionDTO> getAuctionById(@RequestHeader("id") Long userId,
                                                     @RequestHeader("role") String role,
                                                     @PathVariable @Min(0) Long id) {
        AuctionDTO auctionDTO = auctionService.findById(id);
        if (auctionDTO.getTradingCloseTime().compareTo(obtainCurrentDateAndTime()) < 0) {
            auctionDTO.setStatus(Status.COMPLETED);
        }
        ResponseEntity<ProductDTO> response = template.exchange(
                "http://localhost:8084/api/v1/products/" + auctionDTO.getProduct().getId(),
                HttpMethod.GET,
                new HttpEntity<>("some body", createHeadersForSecurity(userId, role)),
                new ParameterizedTypeReference<>() {
                }
        );
        ProductDTO product = response.getBody();
        auctionDTO.setProduct(product);
        return new ResponseEntity<>(auctionDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<AuctionDTO>> getAuctions(@RequestHeader("id") Long userId,
                                                        @RequestHeader("role") String role) {
        List<AuctionDTO> auctions = auctionService.findAll();
        auctions = checkStatusOfAuctions(auctions);
        setAuctionsWithProductData(userId, role, auctions);
        return new ResponseEntity<>(auctions, HttpStatus.OK);
    }

    @RequestMapping(value = "/my", method = RequestMethod.GET)
    public ResponseEntity<List<AuctionDTO>> getUserAuctions(@RequestHeader("id") Long userId,
                                                            @RequestHeader("role") String role) {
        List<AuctionDTO> auctions = auctionService.findAllByUserId(userId);
        auctions = checkStatusOfAuctions(auctions);
        setAuctionsWithProductData(userId, role, auctions);
        return new ResponseEntity<>(auctions, HttpStatus.OK);
    }


    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<String> saveAuction(@RequestHeader("id") Long userId,
                                              @RequestHeader("role") String role,
                                              @Valid @RequestBody AuctionDTO auctionDTO) {
        ResponseEntity<Long> productCreateResponse = template.exchange(
                "http://localhost:8084/api/v1/products",
                HttpMethod.POST,
                new HttpEntity<>(auctionDTO.getProduct(), createHeadersForSecurity(userId, role)),
                Long.class
        );
        Long productId = productCreateResponse.getBody();
        auctionDTO.getProduct().setId(productId);
        auctionDTO.setUserId(userId);
        auctionService.save(auctionDTO);
        return new ResponseEntity<>(String.format(AUCTION_SAVED_MESSAGE), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateAuctionById(@RequestHeader("id") Long userId,
                                                    @RequestHeader("role") String role,
                                                    @Valid @RequestBody AuctionDTO auctionDTO,
                                                    @PathVariable @Min(0) Long id) {
        auctionDTO.setUserId(userId);
        Long productId = auctionService.update(auctionDTO, id);
        ResponseEntity<String> response = template.exchange(
                "http://localhost:8084/api/v1/products/" + productId,
                HttpMethod.PUT,
                new HttpEntity<>(auctionDTO.getProduct(), createHeadersForSecurity(userId, role)),
                String.class
        );
        return new ResponseEntity<>(String.format(AUCTION_UPDATED_MESSAGE, id), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteAuctionById(@RequestHeader("id") Long userId,
                                                    @RequestHeader("role") String role,
                                                    @PathVariable @Min(0) Long id) {
        Long productId = auctionService.delete(id);
        deleteProduct(userId, role, productId);
        return new ResponseEntity<>(String.format(AUCTION_DELETED_MESSAGE, id), HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void deleteAllAuctionsByUserId(@RequestHeader("id") Long userId,
                                          @RequestHeader("role") String role, @PathVariable Long id) {
        List<AuctionDTO> userAuctions = auctionService.findAllByUserId(id);
        for (AuctionDTO userAuction : userAuctions) {
            deleteProduct(userId,role,userAuction.getProduct().getId());
        }
        auctionService.deleteAllByUserId(id);
    }

    private List<AuctionDTO> checkStatusOfAuctions(List<AuctionDTO> auctions) {
        Timestamp currentDateAndTime = obtainCurrentDateAndTime();
        auctions = auctions.stream().map(auctionDTO -> {
                    if (auctionDTO.getTradingCloseTime().compareTo(currentDateAndTime) < 0) {
                        auctionDTO.setStatus(Status.COMPLETED);
                    }
                    return auctionDTO;
                }
        ).toList();
        return auctions;
    }

    private static Timestamp obtainCurrentDateAndTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String dateString = dateFormat.format(date);
        Timestamp currentDateAndTime = Timestamp.valueOf(dateString);
        return currentDateAndTime;
    }

    private HttpHeaders createHeadersForSecurity(Long userId, String role) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("id", userId.toString());
        headers.set("role", role);
        return headers;
    }

    private void setAuctionsWithProductData(Long userId, String role, List<AuctionDTO> auctions) {
        ResponseEntity<List<ProductDTO>> response = template.exchange(
                "http://localhost:8084/api/v1/products",
                HttpMethod.GET,
                new HttpEntity<>("some body", createHeadersForSecurity(userId, role)),
                new ParameterizedTypeReference<>() {
                }
        );
        List<ProductDTO> products = response.getBody();
        if (products != null) {
            for (AuctionDTO auction : auctions) {
                for (ProductDTO product : products) {
                    if (auction.getProduct().getId().equals(product.getId())) {
                        auction.setProduct(product);
                    }
                }
            }
        }
    }

    private void deleteProduct(Long userId, String role, Long productId) {
        ResponseEntity<String> response = template.exchange(
                "http://localhost:8084/api/v1/products/" + productId,
                HttpMethod.DELETE,
                new HttpEntity<>("some body", createHeadersForSecurity(userId, role)),
                String.class
        );
    }
}
