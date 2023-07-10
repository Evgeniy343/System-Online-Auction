package by.evgen.authenticationservice.authentication;

import by.evgen.authenticationservice.model.Role;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {
    private String token;
    private String role;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthenticationResponse that = (AuthenticationResponse) o;
        return Objects.equals(token, that.token) && Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token, role);
    }
}
