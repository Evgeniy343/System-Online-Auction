package by.evgen.authenticationservice.authentication;

import by.evgen.authenticationservice.dto.AuctionUserDTO;
import by.evgen.authenticationservice.model.Role;
import by.evgen.authenticationservice.service.AuthenticationService;
import by.evgen.authenticationservice.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.Map;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationService authService;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final RestTemplate template;

    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public ResponseEntity<String> register(@Valid @RequestBody AuctionUserDTO request) {
        Map<Long, String> userIdAndToken = authService.register(request);
        String token = "";
        Long id = 0L;
        for (Map.Entry<Long, String> userData : userIdAndToken.entrySet()) {
            token = userData.getValue();
            id = userData.getKey();
        }
        if(!request.getRole().equals(Role.ADMIN)){
            template.exchange(
                    "http://localhost:8085/api/v1/basket/create",
                    HttpMethod.POST,
                    new HttpEntity<>("some body",
                            createHeadersForSecurity(id, request.getRole().name())),
                    new ParameterizedTypeReference<>() {
                    }
            );
        }
        return ResponseEntity.ok(token);
    }

    @RequestMapping(value = "/validateToken", method = RequestMethod.GET)
    public ResponseEntity<UserDetailsResponse> checkToken(@RequestParam("token") String token) {
        String userEmail = jwtService.extractUserName(token);
        CustomUserDetails userDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(userEmail);
        if (jwtService.isTokenValid(token, userDetails)) {
            UserDetailsResponse response = new UserDetailsResponse(userDetails);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return null;
        }
    }

    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public String generateToken(@RequestBody AuthenticationRequest request) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                request.getUserName(), request.getPassword()
        );
        Authentication authenticate = authenticationManager.authenticate(authToken);
        if (authenticate.isAuthenticated()) {
            return jwtService.generateToken(request.getUserName());
        } else {
            // todo
            return "Invalid access!";
        }
    }

    private HttpHeaders createHeadersForSecurity(Long userId, String role) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("id", userId.toString());
        headers.set("role", role);
        return headers;
    }
}
