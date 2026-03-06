package com.codecamp.mcp.client.example.api;

import com.codecamp.mcp.client.example.domain.Documents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    private static final Logger log = LoggerFactory.getLogger(ChatController.class);
    private final ChatClient chatClient;

    public ChatController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/chat/docs") // the ai-model should trigger the @Tool from the client (DocumentService)
    public Documents docs(@RequestParam(defaultValue = "Welche Dokumente hat Robin?")  String message)  {
        Documents documents = chatClient.prompt()
                .user(message)
                .call()
                .entity(Documents.class); // use extra type, because it's a list
        log.info("\n\nChat message as entity via Structured Output Converter: \n{}\n", documents);
        return documents;
    }

    @GetMapping("/chat/repos") // the ai-model should trigger the @McpTool from the server via mcp (GitHubRepoService)
    public String repos(@RequestParam(defaultValue = "Welche GitHub Repos hat Robin?")  String message) {
        String response = chatClient.prompt()
                .user(message)
                .call()
                .content();
        log.info("\n\nChat response as raw string: \n{}\n", response);
        return response;
    }

}
