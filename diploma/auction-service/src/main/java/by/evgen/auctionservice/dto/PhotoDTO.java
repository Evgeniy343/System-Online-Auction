package by.evgen.auctionservice.dto;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhotoDTO {
    private Long id;

    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhotoDTO photoDTO = (PhotoDTO) o;
        return Objects.equals(id, photoDTO.id)
                && Objects.equals(name, photoDTO.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
