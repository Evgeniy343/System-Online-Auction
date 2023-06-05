package by.evgen.auctionservice.security;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class UserAuthenticationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        System.out.println("UserAuthenticationFilter Hello!");
        String id = request.getHeader("id");
        String role = request.getHeader("role");
        if(role.equals("anonymous")){
            filterChain.doFilter(request, response);
        }
        else {
            if (SecurityContextHolder.getContext().getAuthentication() == null) {

                UserDetails userDetails = new CustomUserDetails(role, Long.valueOf(id));

                Authentication authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                SecurityContext context = SecurityContextHolder.getContext();
                context.setAuthentication(authToken);
            }
            filterChain.doFilter(request, response);
        }
    }
}
