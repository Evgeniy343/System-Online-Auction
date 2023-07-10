package by.evgen.apigateway.filter;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@AllArgsConstructor
public class AuthenticationFilter implements WebFilter {

    private RouteValidator validator;

    private static final String AUTH_SERVICE_URL = "http://localhost:8083/api/v1/auth";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String typeOfRequest = exchange.getRequest().getMethod().name();
        if(typeOfRequest.equals("OPTIONS")){
            System.out.println("OPTIONS");
        }else {
            if (validator.isSecured.test(exchange.getRequest())) {
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)
                        && typeOfRequest.equals("GET")) {
                    exchange.mutate().request(
                            exchange.getRequest().mutate()
                                    .header("id", "0")
                                    .header("role", "anonymous")
                                    .build()
                    ).build();
                } else {
                    List<String> strings = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION);
                    String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                    if (authHeader != null && authHeader.startsWith("Bearer ")) {
                        String token = authHeader.substring(7);
                        try {
                            WebClient webClient = WebClient.create(AUTH_SERVICE_URL);
                            ResponseEntity<UserDetailsResponse> response = webClient.get()
                                    .uri("/validateToken?token=" + token)
                                    .retrieve()
                                    .toEntity(UserDetailsResponse.class)
                                    .block();

                            UserDetailsResponse body = response.getBody();
                            exchange.mutate().request(
                                    exchange.getRequest().mutate()
                                            .header("id", body.getId().toString())
                                            .header("role", body.getRole().name())
                                            .build()
                            ).build();
                        } catch (Exception e) {
                            System.out.println("invalid access...!");
                            throw new RuntimeException("un authorized access to application");
                        }
                    }
                }
            }
        }
        return chain.filter(exchange);
    }
}
