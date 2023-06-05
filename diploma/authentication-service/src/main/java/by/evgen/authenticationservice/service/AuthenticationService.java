package by.evgen.authenticationservice.service;

import by.evgen.authenticationservice.dto.AuctionUserDTO;
import by.evgen.authenticationservice.mapper.AuctionUserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuctionUserService auctionUserService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuctionUserMapper mapper;

    public Map<Long, String> register(AuctionUserDTO request) {
        AuctionUserDTO savedUser = auctionUserService.save(request);
        var jwtToken = jwtService.generateToken(request.getEmail());
        Map<Long, String> userIdAndToken = new HashMap<>();
        userIdAndToken.put(savedUser.getId(), jwtToken);
        return userIdAndToken;
    }
}
