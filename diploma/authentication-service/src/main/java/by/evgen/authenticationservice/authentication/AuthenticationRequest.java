package by.evgen.authenticationservice.authentication;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationRequest {

    private String userName;
    private String password;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthenticationRequest that = (AuthenticationRequest) o;
        return Objects.equals(userName, that.userName) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, password);
    }
}
