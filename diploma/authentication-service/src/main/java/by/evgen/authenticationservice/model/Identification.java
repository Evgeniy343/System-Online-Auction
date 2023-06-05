package by.evgen.authenticationservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Setter
@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "identification")
public class Identification {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "identity_document")
    @Enumerated(value = EnumType.STRING)
    private IdentityDocument identityDocument;
    @Column(name = "series_and_document_number")
    private String seriesAndDocumentNumber;
    @Column(name = "who_issued_document")
    private String whoIssuedDocument;
    @Column(name = "when_document_was_issued")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Timestamp whenDocumentWasIssued;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Identification that = (Identification) o;
        return Objects.equals(id, that.id)
                && identityDocument == that.identityDocument
                && Objects.equals(seriesAndDocumentNumber, that.seriesAndDocumentNumber)
                && Objects.equals(whoIssuedDocument, that.whoIssuedDocument)
                && Objects.equals(whenDocumentWasIssued, that.whenDocumentWasIssued);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, identityDocument, seriesAndDocumentNumber, whoIssuedDocument,
                whenDocumentWasIssued);
    }
}
