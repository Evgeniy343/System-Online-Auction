package by.evgen.auctionservice.service;

import by.evgen.auctionservice.dto.BasketDTO;
import by.evgen.auctionservice.exception.AuctionNotFoundException;
import by.evgen.auctionservice.mapper.BasketMapper;
import by.evgen.auctionservice.model.Auction;
import by.evgen.auctionservice.model.Basket;
import by.evgen.auctionservice.repository.AuctionRepository;
import by.evgen.auctionservice.repository.BasketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BasketService {

    private static final String AUCTION_NOT_FOUND_MESSAGE = "Auction with id - %s is not found!";
    private final BasketRepository basketRepository;
    private final AuctionRepository auctionRepository;
    private final BasketMapper basketMapper;

    public void deleteAuctionFromBasketById(Long basketId, Long auctionId) {
        Optional<Basket> optionalBasket = basketRepository.findById(basketId);
        if (optionalBasket.isPresent()) {
            Basket basket = optionalBasket.get();
            List<Auction> auctions = basket.getAuctions();
            List<Auction> auctionsWithoutDeletedAuction = auctions.stream()
                    .filter(auction -> !auction.getId().equals(auctionId))
                    .toList();
            basket.setAuctions(auctionsWithoutDeletedAuction);
            basketRepository.save(basket);
        }
    }

    public void addAuctionToBasket(Long basketId, Long auctionId) {
        Optional<Basket> optionalBasket = basketRepository.findById(basketId);
        if (optionalBasket.isPresent()) {
            Basket basket = optionalBasket.get();
            Auction auction = auctionRepository.findById(auctionId).orElseThrow(() ->
                    new AuctionNotFoundException(String.format(AUCTION_NOT_FOUND_MESSAGE, auctionId)));
            basket.getAuctions().add(auction);
            basketRepository.save(basket);
        }
    }

    public BasketDTO getBasketByUserId(Long userId) {
        Optional<Basket> optionalBasket = basketRepository.findBasketByUserId(userId);
        Basket basket;
        if (optionalBasket.isPresent()) {
            basket = optionalBasket.get();
            return basketMapper.convertBasketToBasketDTO(basket);
        } else {
            return null;
        }
    }

    public void createBasket(Long userId) {
        Basket basket = Basket.builder()
                .userId(userId)
                .auctions(new ArrayList<>())
                .build();
        basketRepository.save(basket);
    }
}
