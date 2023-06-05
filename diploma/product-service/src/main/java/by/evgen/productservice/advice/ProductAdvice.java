package by.evgen.productservice.advice;

import by.evgen.productservice.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice("by.evgen.productservice.controller")
public class ProductAdvice {
    @ExceptionHandler({ProductNotFoundException.class})
    public ResponseEntity<String> handleNotFoundException(ProductNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
