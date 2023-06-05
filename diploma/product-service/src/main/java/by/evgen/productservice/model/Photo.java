package by.evgen.productservice.model;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "photo")
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name")
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Photo photo = (Photo) o;
        return Objects.equals(id, photo.id)
                && Objects.equals(name, photo.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
