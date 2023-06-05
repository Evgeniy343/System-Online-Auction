package by.evgen.authenticationservice.dto;

import by.evgen.authenticationservice.model.IdentityDocument;
import lombok.*;

import java.sql.Timestamp;
import java.util.Objects;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IdentificationDTO {
    private Long id;
    private IdentityDocument identityDocument;
    private String seriesAndDocumentNumber;
    private String whoIssuedDocument;
    private Timestamp whenDocumentWasIssued;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IdentificationDTO that = (IdentificationDTO) o;
        return Objects.equals(id, that.id)
                && identityDocument == that.identityDocument
                && Objects.equals(seriesAndDocumentNumber, that.seriesAndDocumentNumber)
                && Objects.equals(whoIssuedDocument, that.whoIssuedDocument)
                && Objects.equals(whenDocumentWasIssued, that.whenDocumentWasIssued);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, identityDocument, seriesAndDocumentNumber, whoIssuedDocument, whenDocumentWasIssued);
    }
}
