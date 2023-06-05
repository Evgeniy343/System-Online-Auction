package by.evgen.userservice.service;

import by.evgen.userservice.dto.AuctionUserDTO;
import by.evgen.userservice.exception.AuctionUserNotFoundException;
import by.evgen.userservice.mapper.AuctionUserMapper;
import by.evgen.userservice.model.AuctionUser;
import by.evgen.userservice.repository.AuctionUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuctionUserService {

    private static final String USER_NOT_FOUND_MESSAGE = "Auction user with id - %s is not found!";
    private static final String AUCTION_USERS_ARE_OUT_OF_STOCK_MESSAGE = "Auction users are out of stock!";
    private final AuctionUserRepository userRepository;
    private final AuctionUserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public AuctionUserDTO findById(Long id) {
        AuctionUser auctionUser = userRepository.findById(id).orElseThrow(() ->
                new AuctionUserNotFoundException(String.format(USER_NOT_FOUND_MESSAGE, id)));
        return userMapper.convertAuctionUserToAuctionUserDTO(auctionUser);
    }

    public List<AuctionUserDTO> findAll() {
        List<AuctionUserDTO> users = userRepository.findAll()
                .stream()
                .map(userMapper::convertAuctionUserToAuctionUserDTO)
                .toList();
        if (users.isEmpty()) {
            throw new AuctionUserNotFoundException(AUCTION_USERS_ARE_OUT_OF_STOCK_MESSAGE);
        } else {
            return users;
        }
    }

    public AuctionUserDTO save(AuctionUserDTO auctionUserDTO) {
        AuctionUser auctionUser = userMapper.convertAuctionUserDTOToAuctionUser(auctionUserDTO);
        auctionUser.setPassword(passwordEncoder.encode(auctionUser.getPassword()));
        AuctionUser savedUser = userRepository.save(auctionUser);
        return userMapper.convertAuctionUserToAuctionUserDTO(savedUser);
    }

    @Transactional
    public AuctionUserDTO update(AuctionUserDTO auctionUserDTO, Long id) {
        AuctionUser auctionUser = userRepository.findById(id).orElseThrow(() ->
                new AuctionUserNotFoundException(String.format(USER_NOT_FOUND_MESSAGE, id)));
        userMapper.updateExisting(auctionUserDTO, auctionUser);
        AuctionUser updatedUser = userRepository.save(auctionUser);
        return userMapper.convertAuctionUserToAuctionUserDTO(updatedUser);
    }

    public void delete(Long id) {
        AuctionUser auctionUser = userRepository.findById(id).orElseThrow(() ->
                new AuctionUserNotFoundException(String.format(USER_NOT_FOUND_MESSAGE, id)));
        userRepository.deleteById(id);
    }
}
