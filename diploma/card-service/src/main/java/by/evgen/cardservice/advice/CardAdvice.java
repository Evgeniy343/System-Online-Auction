package by.evgen.cardservice.advice;

import by.evgen.cardservice.exception.CardNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice("by.evgen.cardservice.controller")
public class CardAdvice {
    @ExceptionHandler({CardNotFoundException.class})
    public ResponseEntity<String> handleNotFoundException(CardNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
