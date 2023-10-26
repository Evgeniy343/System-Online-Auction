package by.evgen.userservice.security;

import by.evgen.userservice.api.model.Role;
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
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            String id = request.getHeader("id");
            String role = request.getHeader("role");

            UserDetails userDetails = new CustomUserDetails(Role.valueOf(role), Long.valueOf(id));

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
