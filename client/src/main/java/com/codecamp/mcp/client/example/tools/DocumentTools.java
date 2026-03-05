package com.codecamp.mcp.client.example.tools;

import com.codecamp.mcp.client.example.domain.Document;
import com.codecamp.mcp.client.example.domain.Documents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DocumentTools {

    private static final Logger log = LoggerFactory.getLogger(DocumentTools.class);

    // in a real application, you would probably fetch the documents from a database or an external service
    private final Documents docs = new Documents(List.of(
            new Document("Robin", "project-notes", "document with notes about the current project"),
            new Document("Daniel", "tec-notes", "document with notes about the tec stack we use in the project"),
            new Document("Patrick", "python-notes", "document with notes about python best practices"),
            new Document("Robin", "spring-notes", "document with notes about spring framework")
    ));

    @Tool(name = "get-all-documents", description = "Get a list of all documents")
    public Documents getAllDocuments() {
        log.info("return all documents");
        return docs;
    }

    @Tool(name = "get-document-by-name", description = "Get a single document by name")
    public Document getDocument(String name) {
        log.info("return document with name: {}", name);
        return docs.items()
                .stream()
                .filter(doc -> doc.name().equals(name))
                .findFirst()
                .orElse(null);
    }
}
