package by.evgen.authenticationservice.service;

import by.evgen.authenticationservice.dto.AuctionUserDTO;
import by.evgen.authenticationservice.exception.AuctionUserNotFoundException;
import by.evgen.authenticationservice.mapper.AuctionUserMapper;
import by.evgen.authenticationservice.model.AuctionUser;
import by.evgen.authenticationservice.repository.AuctionUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuctionUserService {

    private static final String USER_NOT_FOUND_MESSAGE = "Auction user with id - %s is not found!";
    private final AuctionUserRepository userRepository;
    private final AuctionUserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public AuctionUserDTO save(AuctionUserDTO auctionUserDTO) {
        auctionUserDTO.setPassword(passwordEncoder.encode(auctionUserDTO.getPassword()));
        AuctionUser auctionUser = userMapper.convertAuctionUserDTOToAuctionUser(auctionUserDTO);
        AuctionUser savedUser = userRepository.save(auctionUser);
        return userMapper.convertAuctionUserToAuctionUserDTO(savedUser);
    }

    public AuctionUserDTO findByEmail(String email) {
        AuctionUser user = userRepository.findByEmail(email)
                .orElseThrow(() -> new AuctionUserNotFoundException(String.format(USER_NOT_FOUND_MESSAGE, email)));
        return userMapper.convertUserToUserDTOForAuthenticate(user);
    }


}
