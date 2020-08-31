package com.protosstechnology.train.bootexamine.controller;

import com.protosstechnology.train.bootexamine.entity.Document;
import com.protosstechnology.train.bootexamine.service.DocumentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import model.DocumentModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/document")
public class DocumentController {

    @Autowired
    DocumentService documentService;

    @PostMapping
    public ResponseEntity<Document> addDocument(@RequestBody DocumentModel doc) {

        Document document = doc.toDocument();
        log.info("Create Document Id: {} Number: {} Description: {}", document.getId(), document.getDocumentNumber(), document.getDescription());
        documentService.create(document);
        return ResponseEntity.ok().body(document);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get User by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the document",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = Document.class))
                    }),
            @ApiResponse(responseCode = "400", description = "Invalid document id", content = @Content),
            @ApiResponse(responseCode = "404", description = "Document not found", content = @Content)
    })
    public ResponseEntity<Document> getDocument(@PathVariable("id") String id) {
        return ResponseEntity.ok().body(documentService.read(Long.parseLong(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Document> updateDocument(@PathVariable("id") String id, @RequestBody DocumentModel doc) {
        Document document = doc.toDocument();
        document.setId(Long.parseLong(id));
        log.info("Update documentument : {} ", document.getId());
        documentService.update(document);
        return ResponseEntity.ok().body(document);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDocument(@PathVariable("id") String id) {
        String responseStr;
        try {
            documentService.delete(Long.parseLong(id));
            responseStr = "Document id '" + id + "' deleted successfully";
        } catch (Exception exception) {
            responseStr = "Failed to delete document id : " + id;
        }
        return ResponseEntity.ok().body(responseStr);
    }

}
