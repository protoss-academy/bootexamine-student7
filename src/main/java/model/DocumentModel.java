package model;

import javax.validation.constraints.NotNull;

import com.protosstechnology.train.bootexamine.entity.Document;
import lombok.Data;

@Data
public class DocumentModel {
    @NotNull
    private String documentNumber;
    @NotNull
    private String description;

    public Document toDocument() {
        Document doc = new Document();
        doc.setDocumentNumber(this.documentNumber);
        doc.setDescription(this.description);
        return doc;
    }
}
