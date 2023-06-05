package by.evgen.authenticationservice.authentication;

import by.evgen.authenticationservice.dto.AuctionUserDTO;
import by.evgen.authenticationservice.service.AuctionUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final AuctionUserService service;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuctionUserDTO auctionUser = service.findByEmail(username);
        return new CustomUserDetails(auctionUser.getEmail(), auctionUser.getPassword(),
                auctionUser.getRole(), auctionUser.getId());
    }
}
