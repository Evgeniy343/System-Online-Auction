package by.evgen.auctionservice.service;

import by.evgen.auctionservice.dto.BidDTO;
import by.evgen.auctionservice.exception.BidNotFoundException;
import by.evgen.auctionservice.mapper.BidMapper;
import by.evgen.auctionservice.model.Bid;
import by.evgen.auctionservice.repository.BidRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BidService {
    private static final String BID_NOT_FOUND_MESSAGE = "Bid with id - %s is not found!";
    private static final String BIDS_ARE_OUT_OF_STOCK_MESSAGE = "Bids are out of stock!";
    private final BidRepository bidRepository;
    private final BidMapper bidMapper;

    @Transactional
    public BidDTO findById(Long id) {
        Bid bid = bidRepository.findById(id).orElseThrow(() ->
                new BidNotFoundException(String.format(BID_NOT_FOUND_MESSAGE, id)));
        return bidMapper.convertBidToBidDTO(bid);
    }

    @Transactional
    public List<BidDTO> findAllByUserId(Long userId) {
        List<BidDTO> bids = bidRepository.findAllByUserId(userId)
                .stream()
                .map(bidMapper::convertBidToBidDTO)
                .toList();
        if (bids.isEmpty()) {
            throw new BidNotFoundException(BIDS_ARE_OUT_OF_STOCK_MESSAGE);
        } else {
            return bids;
        }
    }

    @Transactional
    public List<BidDTO> findAll() {
        List<BidDTO> bids = bidRepository.findAll()
                .stream()
                .map(bidMapper::convertBidToBidDTO)
                .toList();
        if (bids.isEmpty()) {
            throw new BidNotFoundException(BIDS_ARE_OUT_OF_STOCK_MESSAGE);
        } else {
            return bids;
        }
    }

    public BidDTO save(BidDTO bidDTO) {
        Bid bid = bidMapper.convertBidDTOToBid(bidDTO);
        Bid savedBid = bidRepository.save(bid);
        return bidMapper.convertBidToBidDTO(savedBid);
    }

    public BidDTO update(BidDTO bidDTO, Long id) {
        Bid bid = bidRepository.findById(id).orElseThrow(() ->
                new BidNotFoundException(String.format(BID_NOT_FOUND_MESSAGE, id)));
        bidMapper.updateExisting(bidDTO, bid);
        Bid updatedBid = bidRepository.save(bid);
        return bidMapper.convertBidToBidDTO(updatedBid);
    }

    public void delete(Long id) {
        Bid bid = bidRepository.findById(id).orElseThrow(() ->
                new BidNotFoundException(String.format(BID_NOT_FOUND_MESSAGE, id)));
        bidRepository.deleteById(id);
    }
}
