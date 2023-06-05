package by.evgen.cardservice.payment;

import com.stripe.exception.StripeException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/payment")
public class PaymentController {

    private PaymentService service;

    @PostMapping
    public ResponseEntity<String> completePayment(@RequestBody PaymentRequest request) throws StripeException {
        String chargeId = service.charge(request);
        return chargeId != null ? new ResponseEntity<>(chargeId, HttpStatus.OK) :
                new ResponseEntity<>("Please check the credit card details entered", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public String handleError(StripeException ex) {
        return ex.getMessage();
    }

}
