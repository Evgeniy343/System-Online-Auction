package by.evgen.authenticationservice.exception;

public class AuctionUserNotFoundException extends RuntimeException {
    public AuctionUserNotFoundException(String message) {
        super(message);
    }
}
