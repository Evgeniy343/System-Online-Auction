package by.evgen.auctionservice.service;

import by.evgen.auctionservice.dto.AuctionDTO;
import by.evgen.auctionservice.exception.AuctionNotFoundException;
import by.evgen.auctionservice.mapper.AuctionMapper;
import by.evgen.auctionservice.model.Auction;
import by.evgen.auctionservice.repository.AuctionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuctionService {

    private static final String AUCTION_NOT_FOUND_MESSAGE = "Auction with id - %s is not found!";
    private static final String AUCTIONS_ARE_OUT_OF_STOCK_MESSAGE = "Auctions are out of stock!";
    private final AuctionRepository auctionRepository;
    private final AuctionMapper auctionMapper;

    public AuctionDTO findById(Long id) {
        Auction auction = auctionRepository.findById(id).orElseThrow(() ->
                new AuctionNotFoundException(String.format(AUCTION_NOT_FOUND_MESSAGE, id)));
        return auctionMapper.convertAuctionToAuctionDTO(auction);
    }

    public List<AuctionDTO> findAll() {
        List<AuctionDTO> auctions = auctionRepository.findAll()
                .stream()
                .map(auctionMapper::convertAuctionToAuctionDTO)
                .toList();
        if (auctions.isEmpty()) {
            throw new AuctionNotFoundException(AUCTIONS_ARE_OUT_OF_STOCK_MESSAGE);
        } else {
            return auctions;
        }
    }

    public List<AuctionDTO> findAllByUserId(Long userId) {
        List<AuctionDTO> auctions = auctionRepository.findAllByUserId(userId)
                .stream()
                .map(auctionMapper::convertAuctionToAuctionDTO)
                .toList();
        if (auctions.isEmpty()) {
            throw new AuctionNotFoundException(AUCTIONS_ARE_OUT_OF_STOCK_MESSAGE);
        } else {
            return auctions;
        }
    }


    @Transactional
    public AuctionDTO save(AuctionDTO auctionDTO) {
        Auction auction = auctionMapper.convertAuctionDTOToAuction(auctionDTO);
        Auction savedAuction = auctionRepository.save(auction);
        return auctionMapper.convertAuctionToAuctionDTO(savedAuction);
    }

    public Long update(AuctionDTO auctionDTO, Long id) {
        Auction auction = auctionRepository.findById(id).orElseThrow(() ->
                new AuctionNotFoundException(String.format(AUCTION_NOT_FOUND_MESSAGE, id)));
        auctionMapper.updateExisting(auctionDTO, auction);
        Auction updatedAuction = auctionRepository.save(auction);
        return updatedAuction.getProductId();
    }

    @Transactional
    public Long delete(Long id) {
        Auction auction = auctionRepository.findById(id).orElseThrow(() ->
                new AuctionNotFoundException(String.format(AUCTION_NOT_FOUND_MESSAGE, id)));
        auctionRepository.deleteById(id);
        return auction.getProductId();
    }
}
