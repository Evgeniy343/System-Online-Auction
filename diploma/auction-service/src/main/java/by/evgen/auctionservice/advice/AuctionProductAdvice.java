package by.evgen.auctionservice.advice;

import by.evgen.auctionservice.exception.AuctionNotFoundException;
import by.evgen.auctionservice.exception.BidNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice("by.evgen.auctionservice.controller")
public class AuctionProductAdvice {
    @ExceptionHandler({AuctionNotFoundException.class})
    public ResponseEntity<String> handleAuctionNotFoundException(AuctionNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({BidNotFoundException.class})
    public ResponseEntity<String> handleBidNotFoundException(BidNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
