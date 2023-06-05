package by.evgen.auctionservice.exception;

public class BidNotFoundException extends RuntimeException {
    public BidNotFoundException(String message) {
        super(message);
    }
}
