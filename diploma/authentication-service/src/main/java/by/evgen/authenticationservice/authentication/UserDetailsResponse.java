package by.evgen.authenticationservice.authentication;

import by.evgen.authenticationservice.model.Role;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsResponse {
    private Long id;
    private Role role;

    public UserDetailsResponse(CustomUserDetails userDetails) {
        this.id = userDetails.getId();
        this.role = userDetails.getRole();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDetailsResponse that = (UserDetailsResponse) o;
        return Objects.equals(id, that.id)
                && role == that.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, role);
    }
}
