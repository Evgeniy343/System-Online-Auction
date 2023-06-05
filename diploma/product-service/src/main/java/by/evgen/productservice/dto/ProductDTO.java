package by.evgen.productservice.dto;

import by.evgen.productservice.model.Category;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long id;
    private String name;
    private Category category;
    private String description;
    private List<PhotoDTO> photos;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDTO that = (ProductDTO) o;
        return Objects.equals(id, that.id)
                && Objects.equals(name, that.name)
                && category == that.category
                && Objects.equals(description, that.description)
                && Objects.equals(photos, that.photos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, category, description, photos);
    }
}
