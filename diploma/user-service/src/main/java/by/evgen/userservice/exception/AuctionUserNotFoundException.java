package by.evgen.userservice.exception;

public class AuctionUserNotFoundException extends RuntimeException {
    public AuctionUserNotFoundException(String message) {
        super(message);
    }
}
