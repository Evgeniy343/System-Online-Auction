package by.evgen.userservice.model;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Setter
@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "copies_of_documents")
public class CopiesOfDocuments {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "copy_of_document_page_with_photo")
    private String copyOfDocumentPageWithPhoto;
    @Column(name = "copy_of_document_page_with_personal_number")
    private String copyOfDocumentPageWithPersonalNumber;
    @Column(name = "copy_of_page_of_document_residence_permit")
    private String copyOfPageOfDocumentResidencePermit;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CopiesOfDocuments that = (CopiesOfDocuments) o;
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