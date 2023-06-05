package by.evgen.apigateway.filter;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsResponse {
    private Long id;
    private String username;
    private String password;
    private Role role;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDetailsResponse that = (UserDetailsResponse) o;
        return Objects.equals(id, that.id)
                && Objects.equals(username, that.username)
                && Objects.equals(password, that.password)
                && role == that.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, role);
    }
}
