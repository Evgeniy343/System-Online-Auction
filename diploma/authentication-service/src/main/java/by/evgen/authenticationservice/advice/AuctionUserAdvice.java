package by.evgen.authenticationservice.advice;

import by.evgen.authenticationservice.exception.AuctionUserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice("by.evgen.authenticationservice.controller")
public class AuctionUserAdvice {
    @ExceptionHandler({AuctionUserNotFoundException.class})
    public ResponseEntity<String> handleNotFoundException(AuctionUserNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
