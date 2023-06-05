package by.evgen.auctionservice.exception;

public class AuctionNotFoundException extends RuntimeException {
    public AuctionNotFoundException(String message) {
        super(message);
    }
}
