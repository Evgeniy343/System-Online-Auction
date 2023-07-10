package by.evgen.authenticationservice.authentication;

import by.evgen.authenticationservice.dto.AuctionUserDTO;
import by.evgen.authenticationservice.model.Role;
import by.evgen.authenticationservice.service.AuctionUserService;
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
import java.sql.Date;
import java.util.Base64;
import java.util.HashMap;
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
    public ResponseEntity<AuthenticationResponse> register(@Valid @RequestBody AuctionUserDTO request) {
        Map<Long, String> userIdAndToken = authService.register(request);
        String token = "";
        Long id = 0L;
        for (Map.Entry<Long, String> userData : userIdAndToken.entrySet()) {
            token = userData.getValue();
            id = userData.getKey();
        }
        if (!request.getRole().equals(Role.ADMIN)) {
            template.exchange(
                    "http://localhost:8085/api/v1/basket/create",
                    HttpMethod.POST,
                    new HttpEntity<>("some body",
                            createHeadersForSecurity(id, request.getRole().name())),
                    new ParameterizedTypeReference<>() {
                    }
            );
        }
        return new ResponseEntity<>(new AuthenticationResponse(token, request.getRole().name()), HttpStatus.OK);
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

    @RequestMapping(value = "/refreshToken", method = RequestMethod.POST)
    public ResponseEntity<AuthenticationResponse> refreshToken(@RequestParam("token") String token) {
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String[] parts = token.split("\\."); // Splitting header, payload and signature
        String payload = new String(decoder.decode(parts[1]));
        String payloadData = payload.substring(1, payload.length() - 1);
        String[] payloadParts = payloadData.split(",");
        Map<String, String> tokenDataParts = new HashMap<>();
        for (String payloadPart : payloadParts) {
            String[] tokenData = payloadPart.split(":");
            tokenDataParts.put(tokenData[0].replaceAll("\"", ""),
                    tokenData[1].replaceAll("\"", ""));
        }
        String userName = tokenDataParts.get("sub");
        String expiration = tokenDataParts.get("exp");
        System.out.println(expiration);
        CustomUserDetails userDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(userName);
        if (jwtService.isTokenExpired(expiration)) {
            System.out.println("old token - " + token);
            String refreshToken = jwtService.generateToken(userName);
            System.out.println("new token - " + refreshToken);
            return new ResponseEntity<>(new AuthenticationResponse(refreshToken, userDetails.getRole().name())
                    , HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new AuthenticationResponse(token, userDetails.getRole().name())
                    , HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public ResponseEntity<AuthenticationResponse> generateToken(@RequestBody AuthenticationRequest request) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                request.getUserName(), request.getPassword()
        );
        Authentication authenticate = authenticationManager.authenticate(authToken);
        if (authenticate.isAuthenticated()) {
            CustomUserDetails userDetails = (CustomUserDetails) userDetailsService
                    .loadUserByUsername(request.getUserName());
            return new ResponseEntity<>(new AuthenticationResponse(jwtService.generateToken(request.getUserName()),
                    userDetails.getRole().name()), HttpStatus.OK);
        } else {
            // todo
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    private HttpHeaders createHeadersForSecurity(Long userId, String role) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("id", userId.toString());
        headers.set("role", role);
        return headers;
    }
}
