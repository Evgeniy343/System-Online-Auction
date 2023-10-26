package by.evgen.userservice.api.dto;

import lombok.*;

import java.util.Objects;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CopiesOfDocumentsDTO {
    private Long id;
    private String copyOfDocumentPageWithPhoto;
    private String copyOfDocumentPageWithPersonalNumber;
    private String copyOfPageOfDocumentResidencePermit;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CopiesOfDocumentsDTO that = (CopiesOfDocumentsDTO) o;
        return Objects.equals(id, that.id)
                && Objects.equals(copyOfDocumentPageWithPhoto, that.copyOfDocumentPageWithPhoto)
                && Objects.equals(copyOfDocumentPageWithPersonalNumber, that.copyOfDocumentPageWithPersonalNumber)
                && Objects.equals(copyOfPageOfDocumentResidencePermit, that.copyOfPageOfDocumentResidencePermit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, copyOfDocumentPageWithPhoto, copyOfDocumentPageWithPersonalNumber,
                copyOfPageOfDocumentResidencePermit);
    }
}